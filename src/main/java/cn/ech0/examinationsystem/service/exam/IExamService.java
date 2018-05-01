package cn.ech0.examinationsystem.service.exam;

import cn.ech0.examinationsystem.request.ExamAnswer;
import cn.ech0.examinationsystem.wrapper.ExamPaperWrapper;
import cn.ech0.examinationsystem.wrapper.ExamResultWrapper;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 07:12 AM
 */
public interface IExamService {
    /**
     * 根据试卷Id获取试卷，包含题目详情。注意每题的分值要一一对应。
     *
     *  并生成考试记录
     * @param userId
     * @param userName
     * @param examPaperId
     * @param isExam
     * @param examRecordId
     *
     * @return
     */
    ExamPaperWrapper obtainExamPaper(String userId, String userName ,Long examPaperId,boolean isExam , Long examRecordId);


    ExamResultWrapper gradeExamPaper(String userId ,ExamAnswer examAnswer);


}
