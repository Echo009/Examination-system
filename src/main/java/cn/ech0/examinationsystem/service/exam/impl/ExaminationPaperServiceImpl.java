package cn.ech0.examinationsystem.service.exam.impl;

import cn.ech0.examinationsystem.comparetor.QuestionEntityComparator;
import cn.ech0.examinationsystem.convertor.ExaminationPaperEntityConvertor;
import cn.ech0.examinationsystem.dao.exam.ExaminationPaperDao;
import cn.ech0.examinationsystem.dao.exam.PracticeRecordDao;
import cn.ech0.examinationsystem.dao.exam.QuestionDao;
import cn.ech0.examinationsystem.dto.exam.ExaminationPaperDTO;
import cn.ech0.examinationsystem.entity.exam.ExaminationPaperEntity;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.enums.ResponseCodeEnum;
import cn.ech0.examinationsystem.exception.BaseServerException;
import cn.ech0.examinationsystem.service.exam.IExaminationPaperService;
import cn.ech0.examinationsystem.setting.ExamPreference;
import cn.ech0.examinationsystem.util.DateUtil;
import cn.ech0.examinationsystem.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 04:21 PM
 */

@Slf4j
@Service
public class ExaminationPaperServiceImpl implements IExaminationPaperService {

    @Autowired
    private ExaminationPaperDao examinationPaperDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private PracticeRecordDao practiceRecordDao;


    /**
     * 根据试题类型以及题目数量以及偏好设定由系统自动生成试卷
     *
     * @param userId
     * @param categories not null
     * @param titleNum
     * @param preference
     * @return
     */
    @Override
    public ExaminationPaperDTO genExaminaionPaper(String userId, Integer[] categories, Integer titleNum, ExamPreference preference) {
        List<Long> questionIdList;
        if (preference.isOnlyNew()) {
            // 仅出新题
            // ①取出所有做过的题目，满足给定条件（categories）
            List<Long> practicedQuestions = practiceRecordDao.findAllPracticedQuestionsWithInCategories(userId, categories);
            // ②从题库中取出不在这里边的题目id,且满足给定分类条件
            List<Long> neverPracticedQuestions = questionDao.findAllQuestionsNotInList(practicedQuestions, categories);
            if (neverPracticedQuestions.size() < titleNum) {
                throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "符合条件的题目数量不够组卷！");
            }
            // ③shuffle
            Collections.shuffle(neverPracticedQuestions);
            questionIdList = neverPracticedQuestions.stream().limit(titleNum).collect(Collectors.toList());
        } else if (preference.isOnlyError()) {
            // 仅出错题
            // ①先取出所有错题的id，且满足给定分类条件
            List<Long> errorQuestions = practiceRecordDao.findAllErrorQuestionsWithInCategories(userId, categories);
            // ②shuffle
            Collections.shuffle(errorQuestions);
            if (errorQuestions.size() < titleNum) {
                throw new BaseServerException(ResponseCodeEnum.ERROR.getCode(), "符合条件的题目数量不够组卷！");
            }
            questionIdList = errorQuestions.stream().limit(titleNum).collect(Collectors.toList());
        } else {
            List<Long> questionIds = questionDao.findAllQuestions(categories);
            questionIdList = questionIds.stream().collect(Collectors.toList());
        }
        List<QuestionEntity> questionEntities = questionDao.findAllQuestionsInList(questionIdList);
        // sort
        Collections.sort(questionEntities, new QuestionEntityComparator());

        Long[] questions = new Long[titleNum];
        String[] answers = new String[titleNum];
        HashSet<Integer> types = new HashSet<>();
        for (int i = 0; i < questions.length; i++) {
            questions[i] = questionEntities.get(i).getId();
            answers[i] = questionEntities.get(i).getAnswer();
            types.add(questionEntities.get(i).getType());
        }
        ExaminationPaperEntity examinationPaperEntity = new ExaminationPaperEntity();
        examinationPaperEntity.setTitleNum(titleNum);
        examinationPaperEntity.setCreateTime(DateUtil.getCurrentDate());
        examinationPaperEntity.setQuestions(questions);
        examinationPaperEntity.setAnswers(answers);
        examinationPaperEntity.setCategories(categories);
        Integer[] _types = types.toArray(new Integer[]{});
        examinationPaperEntity.setTypes(_types);
        examinationPaperEntity.setTitle("System_"+ KeyUtil.genUniqueKey());
        examinationPaperEntity = examinationPaperDao.saveAndFlush(examinationPaperEntity);

        return ExaminationPaperEntityConvertor.toExaminationPaperDTO(examinationPaperEntity);
    }

    /**
     * 指定题目以及分数生成试卷
     *
     * @param userId
     * @param userName
     * @param title
     * @param questions
     * @param grades
     * @param duration
     * @return
     */
    @Override
    public ExaminationPaperDTO addExaminationPaper(String userId,String userName, String title, Long[] questions, Integer[] grades,Integer duration) {

        List<QuestionEntity> questionEntities = questionDao.findAllQuestionsInList(Arrays.asList(questions));
        // sort
        Collections.sort(questionEntities, new QuestionEntityComparator());
        Long[] questionIds = new Long[questionEntities.size()];
        String[] answers = new String[questionEntities.size()];
        HashSet<Integer> types = new HashSet<>();
        HashSet<Integer> categories = new HashSet<>();
        for (int i = 0; i < questionEntities.size(); i++) {
            questionIds[i] = questionEntities.get(i).getId();
            answers[i] = questionEntities.get(i).getAnswer();
            types.add(questionEntities.get(i).getType());
            categories.add(questionEntities.get(i).getCategory());
        }

        ExaminationPaperEntity examinationPaperEntity = new ExaminationPaperEntity();
        examinationPaperEntity.setCreateTime(DateUtil.getCurrentDate());
        examinationPaperEntity.setCreatorId(userId);
        examinationPaperEntity.setCreatorName(userName);
        examinationPaperEntity.setTitle(title);
        examinationPaperEntity.setTitleNum(questionEntities.size());
        examinationPaperEntity.setTypes(types.toArray(new Integer[]{}));
        examinationPaperEntity.setCategories(categories.toArray(new Integer[]{}));
        examinationPaperEntity.setQuestions(questionIds);
        examinationPaperEntity.setAnswers(answers);
        examinationPaperEntity.setGrades(grades);
        examinationPaperEntity.setMaxExamDuration(duration);

        examinationPaperEntity = examinationPaperDao.saveAndFlush(examinationPaperEntity);
        return ExaminationPaperEntityConvertor.toExaminationPaperDTO(examinationPaperEntity);

    }
}
