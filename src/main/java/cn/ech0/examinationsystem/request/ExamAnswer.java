package cn.ech0.examinationsystem.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 08:51 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExamAnswer {
    /**
     * 试卷id
     */
    private Long examPaperId;
    /**
     * 考试记录的id
     */
    private Long examRecordId;
    /**
     * 答案
     */
    private List<AnswerItem> answers;


}
