package cn.ech0.examinationsystem.service.exam;

import cn.ech0.examinationsystem.dto.exam.ExaminationPaperDTO;
import cn.ech0.examinationsystem.setting.ExamPreference;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 03:51 PM
 */
public interface IExaminationPaperService {



    /**
     * 根据试题类型以及题目数量以及偏好设定由系统自动生成试卷
     * @param userId
     * @param categorys
     * @param titleNum
     * @param perference
     * @return
     */
    ExaminationPaperDTO genExaminaionPaper(String userId ,Integer [] categorys, Integer titleNum, ExamPreference perference);

    /**
     * 指定题目以及分数生成试卷
     * @param userId
     * @param userName
     * @param title
     * @param questions
     * @param grades
     * @param duration
     * @return
     */
    ExaminationPaperDTO addExaminationPaper(String userId,String userName ,String title, Long[] questions,Integer[] grades,Integer duration);




}
