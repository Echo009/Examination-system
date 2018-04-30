package cn.ech0.examinationsystem.dto.exam;

import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:29 PM
 */
@Data
public class QuestionDTO {


    private Long id;
    /**
     * 题目序号
     */
    private Integer seq;

    private Integer category;

    private Integer type;

    private String title;

    private String choices;

    private String answer;

    private String titleImgs;

    private String answerImgs;

    /**
     * 错误率 保留两位
     */
    private String errorRate;
}
