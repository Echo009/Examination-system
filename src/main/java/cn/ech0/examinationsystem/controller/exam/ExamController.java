package cn.ech0.examinationsystem.controller.exam;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.request.AnswerItem;
import cn.ech0.examinationsystem.request.ExamAnswer;
import cn.ech0.examinationsystem.response.BaseResponse;
import cn.ech0.examinationsystem.service.exam.IExamService;
import cn.ech0.examinationsystem.util.JsonUtil;
import cn.ech0.examinationsystem.wrapper.ExamPaperWrapper;
import cn.ech0.examinationsystem.wrapper.ExamResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/03/2018 08:51 PM
 */
@Slf4j
@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "*")
public class ExamController extends BaseController {
    @Autowired
    private IExamService examService;

    @RequestMapping("/startExam")
    public BaseResponse startExam(@RequestParam Long examPaperId) {
        ExamPaperWrapper examPaperWrapper =
                examService.obtainExamPaper(getCurrentUserInfo().getUserId(),
                        getCurrentUserInfo().getUserName(),
                        examPaperId, true, null);
        return new BaseResponse(true, examPaperWrapper);
    }


    @RequestMapping("/grade")
    public BaseResponse grade( @RequestParam Long examRecordId, @RequestParam Long examPaperId, @RequestParam String answers) {
        ExamAnswer answer = new ExamAnswer();
        answer.setExamPaperId(examPaperId);
        answer.setExamRecordId(examRecordId);
        AnswerItem[] answerItems =null;
        try{
            answerItems = JsonUtil.toBean(answers, AnswerItem[].class);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        answer.setAnswers(Arrays.asList(answerItems));
       ExamResultWrapper examPaperWrapper = examService.gradeExamPaper(getCurrentUserInfo().getUserId(), answer);
        return new BaseResponse(true, examPaperWrapper);

    }


}
