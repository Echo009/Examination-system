package cn.ech0.examinationsystem.service.exam.impl;

import cn.ech0.examinationsystem.comparetor.AnswerItemComparator;
import cn.ech0.examinationsystem.comparetor.QuestionEntityComparator;
import cn.ech0.examinationsystem.convertor.QuestionEntityConvertor;
import cn.ech0.examinationsystem.dao.exam.ExamRecordDao;
import cn.ech0.examinationsystem.dao.exam.ExaminationPaperDao;
import cn.ech0.examinationsystem.dao.exam.PracticeRecordDao;
import cn.ech0.examinationsystem.dao.exam.QuestionDao;
import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import cn.ech0.examinationsystem.entity.exam.ExamRecordEntity;
import cn.ech0.examinationsystem.entity.exam.ExaminationPaperEntity;
import cn.ech0.examinationsystem.entity.exam.PracticeRecordEntity;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.enums.ResponseCodeEnum;
import cn.ech0.examinationsystem.enums.exam.ExamStatusEnum;
import cn.ech0.examinationsystem.exception.BaseServerException;
import cn.ech0.examinationsystem.request.AnswerItem;
import cn.ech0.examinationsystem.request.ExamAnswer;
import cn.ech0.examinationsystem.service.exam.IExamService;
import cn.ech0.examinationsystem.util.DateUtil;
import cn.ech0.examinationsystem.util.GradeUtil;
import cn.ech0.examinationsystem.util.JsonUtil;
import cn.ech0.examinationsystem.wrapper.ExamPaperWrapper;
import cn.ech0.examinationsystem.wrapper.ExamResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 07:35 AM
 */

@Service
@Slf4j
public class ExamServiceImpl implements IExamService {

    @Autowired
    private ExaminationPaperDao examinationPaperDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private ExamRecordDao examRecordDao;
    @Autowired
    private PracticeRecordDao practiceRecordDao;


    // todo test

    /**
     * 根据试卷Id获取试卷，包含题目详情。注意每题的分值要一一对应。
     * <p>
     * 根据需要生成考试记录或者附上错题信息
     *
     * @param userId
     * @param userName
     * @param examPaperId
     * @param isExam       false if only for view exam-paper
     * @param examRecordId null if not to view record
     * @return
     */
    @Override
    public ExamPaperWrapper obtainExamPaper(String userId, String userName, Long examPaperId, boolean isExam, Long examRecordId) {

        ExaminationPaperEntity examinationPaperEntity = examinationPaperDao.findOne(examPaperId);
        if (examinationPaperEntity == null) {
            throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "该试卷不存在 ！");
        }
        Long[] questionIds = examinationPaperEntity.getQuestions();
        List<QuestionEntity> questionEntities = questionDao.findAllQuestionsInList(Arrays.asList(questionIds));
        // 按照生成试卷的既定规则排序，保证顺序和试卷中原有一致
        Collections.sort(questionEntities, new QuestionEntityComparator());
        List<QuestionDTO> questionDTOList = new ArrayList<>(questionEntities.size());
        questionEntities.forEach(e ->
                questionDTOList.add(QuestionEntityConvertor.convertoQuestionDTO(e))
        );
        // set seq ! important
        for (int i = 0; i < questionDTOList.size(); i++) {
            questionDTOList.get(i).setSeq(i);
        }
        Integer[] grades = examinationPaperEntity.getGrades();
        // 填充分数
        if (grades != null) {
            for (int i = 0; i < grades.length; i++) {
                questionDTOList.get(i).setGrade(grades[i]);
            }
        }

        ExamPaperWrapper examPaperWrapper = new ExamPaperWrapper();
        BeanUtils.copyProperties(examinationPaperEntity, examPaperWrapper);
        if (isExam) {
            // 生成考试记录
            ExamRecordEntity examRecordEntity = new ExamRecordEntity();
            examRecordEntity.setUserId(userId);
            examRecordEntity.setUserName(userName);
            examRecordEntity.setExaminationPaperId(examinationPaperEntity.getId());
            examRecordEntity.setTitle(examinationPaperEntity.getTitle());
            examRecordEntity.setTypes(examinationPaperEntity.getTypes());
            examRecordEntity.setCategories(examinationPaperEntity.getCategories());
            examRecordEntity = examRecordDao.saveAndFlush(examRecordEntity);
            // set record id
            examPaperWrapper.setExamRecordId(examRecordEntity.getId());
        } else if (examRecordId != null) {
            // 查看考试记录, 需要包含错题信息
            ExamRecordEntity examRecordEntity = examRecordDao.findOne(examRecordId);
            if (examRecordEntity == null) {
                throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "考试记录不存在 ！");
            }
            if (examRecordEntity.getStatus().equals(ExamStatusEnum.UNFINISHED.getCode())) {
                throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "考试暂未完成 ！");
            }
            // need valid seq
            Integer[] errorQuestionSeqs = examRecordEntity.getErrorQuestionSeqs();
            String[] errorAnswers = examRecordEntity.getErrorAnswers();
            for (int i = 0; i < errorQuestionSeqs.length; i++) {
                // 填充错误答案
                questionDTOList.get(i).setErrorAnswer(errorAnswers[i]);
            }
            examPaperWrapper.setExamRecordId(examRecordId);
        }
        examPaperWrapper.setQuestions(questionDTOList);
        return examPaperWrapper;
    }

    /**
     * 判卷，需存储错误的答案
     *
     * @param userId
     * @param examAnswer
     * @return
     */
    @Override
    public ExamResultWrapper gradeExamPaper(String userId, ExamAnswer examAnswer) {
        ExaminationPaperEntity examinationPaperEntity = examinationPaperDao.findOne(examAnswer.getExamPaperId());
        if (examinationPaperEntity == null) {
            throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "该试卷不存在 ！");
        }
        ExamRecordEntity examRecordEntity = examRecordDao.findOne(examAnswer.getExamRecordId());
        if (examRecordEntity == null) {
            throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "该考试记录不存在 ！");
        }
        // 设置完成时间
        examRecordEntity.setCompleteTime(new Date());
        ExamResultWrapper examResultWrapper = new ExamResultWrapper();
        examResultWrapper.setExamRecordId(examAnswer.getExamRecordId());
        examResultWrapper.setUseTime(DateUtil.getInterval(examRecordEntity.getBeginTime(),
                examRecordEntity.getCompleteTime()));

        List<AnswerItem> answerItems = examAnswer.getAnswers();
        List<ExamResultWrapper.GradeItem> gradeResults = new ArrayList<>(answerItems.size());
        // 总分
        int[] totalGrade = new int[1];
        // 错题序号
        Integer[] errorQuestionSeqs = new Integer[answerItems.size()];
        // 错误题目ID
        Long[] errorQuestionIds = new Long[answerItems.size()];
        // 错误答案
        String[] errorAnswers = new String[answerItems.size()];
        // 正确答案
        String[] correctAnswers = examinationPaperEntity.getAnswers();
        // index for lambada
        int[] currentIndex = new int[1];
        // 分值不为空，需要按试题在试卷中的编号顺序判卷评分
        Integer[] grades = examinationPaperEntity.getGrades();
        final boolean withGradeData ;
        if (grades != null) {
            withGradeData =true;

        } else {
            withGradeData =false;
            // 默认分值都为1

        }
        // sort to promise valid seq
        Collections.sort(answerItems, new AnswerItemComparator());
        try {
            answerItems.forEach(e -> {
                ExamResultWrapper.GradeItem gradeItem = new ExamResultWrapper.GradeItem();
                gradeItem.setSeq(e.getSeq());

                // 生成做题记录
                PracticeRecordEntity practiceRecordEntity = practiceRecordDao.findByUserIdAndQuestionId(userId, e.getQuestionId());
                if (practiceRecordEntity == null) {
                    // 初始化做题记录
                    practiceRecordEntity = new PracticeRecordEntity();
                    practiceRecordEntity.setUserId(userId);
                    practiceRecordEntity.setQuestionId(e.getQuestionId());
                    practiceRecordEntity.setLastUseTime(new Date());
                }
                log.info("seq {} answer is {}",e.getSeq(),e.getAnswer());
                log.info("correct answer is {}",correctAnswers[e.getSeq()]);

                // 注意，这里没有执行trim操作，前端格式需要严格一致。
                if (!e.getAnswer().equals(correctAnswers[e.getSeq()])) {
                    errorQuestionSeqs[currentIndex[0]] = e.getSeq();
                    errorQuestionIds[currentIndex[0]] = e.getQuestionId();
                    errorAnswers[currentIndex[0]++] = e.getAnswer();
                    // 错误次数+1
                    practiceRecordEntity.setErrorTimes(practiceRecordEntity.getErrorTimes()+1);
                    gradeItem.setStatus(false);
                }
                else {
                    gradeItem.setStatus(true);
                    if (withGradeData) {
                        totalGrade[0] += grades[e.getSeq()];
                    } else {
                        totalGrade[0]++ ;
                    }
                }
                practiceRecordDao.saveAndFlush(practiceRecordEntity);
                log.debug("save practice record : {}", practiceRecordEntity);
                gradeResults.add(gradeItem);
            });
        } catch (Exception e) {
            throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "答题信息有误 ！请检查序号");
        }
        if (withGradeData) {
            examRecordEntity.setGrade(totalGrade[0]+"");
        }
        else {
            examRecordEntity.setGrade(GradeUtil.formatGrade(totalGrade[0]/examinationPaperEntity.getTitleNum()));
        }
        examRecordEntity.setErrorQuestions(errorQuestionIds);
        examRecordEntity.setErrorQuestionSeqs(errorQuestionSeqs);
        examRecordEntity.setErrorAnswers(errorAnswers);
        examRecordEntity.setStatus(ExamStatusEnum.FINISHED.getCode());
        examRecordDao.saveAndFlush(examRecordEntity);
        log.info(" ExamRecord is {}" , examRecordEntity);
        examResultWrapper.setGradeResults(gradeResults);
        examResultWrapper.setGrade(examRecordEntity.getGrade());
        return examResultWrapper;
    }
}
