package cn.ech0.examinationsystem.dto.exam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:29 PM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QuestionDTO {


    private Long id;
    /**
     * 题目序号
     */
    private Integer seq;
    /**
     * 分值
     */
    private Integer grade;

    private Integer category;

    private Integer type;

    private String title;

    private String choices;
    /**
     * 正确答案
     */
    private String answer;
    /**
     * 错误答案
     */
    private String errorAnswer;

    private String titleImgs;

    private String answerImgs;

    /**
     * 错误率 保留两位
     */
    private String errorRate;

    private Integer cloneTimes;

    private Integer favoriteTimes;

    private String updateTime;
}
