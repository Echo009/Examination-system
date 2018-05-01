package cn.ech0.examinationsystem.wrapper;

import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 09:00 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExamResultWrapper {

    private Long examRecordId;

    private String grade;
    /**
     * 总用时
     */
    private String useTime;
    /**
     * 判题结果，仅含seq，以及错对信息
     */
    private List<GradeItem> gradeResults;

    @Data
    public static class GradeItem{

        private Integer seq;
        /**
         * should be false if  answer is incorrect
         */
        private boolean status;
    }

}
