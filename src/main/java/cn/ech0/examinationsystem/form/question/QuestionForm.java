package cn.ech0.examinationsystem.form.question;

import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 12:12 PM
 */
@Data
public class QuestionForm {

    private Long id;

    private String userId;

    private String userName;

    private String category;

    private Integer type;

    private String title;

    private String choices;

    private String answer;
    /**
     * 是否仅有一个标题图片，如果为true那么titleImgs必须有效
     */
    private Boolean onlyImgTitle;
    /**
     * 可选
     */
    private String titleImgs;
    /**
     * 可选
     */
    private String answerImgs;


}
