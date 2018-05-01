package cn.ech0.examinationsystem.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 01:24 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AnswerItem {
    /**
     * 试卷id
     */
    private Long examPaperId;
    /**
     * 题目序号
     */
    private Integer seq;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 答案
     */
    private String answer;

}
