package cn.ech0.examinationsystem.entity.exam;


import cn.ech0.examinationsystem.enums.question.QuestionStatusEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:17 PM
 *
 * @author Ech0
 */
@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_q", columnNames = {"userId", "type", "title"}),
                // 同一道题目仅可以克隆一次
                @UniqueConstraint(name = "idx_clone", columnNames = {"userId", "cloneFrom"}),
        },
        indexes = {
                @Index(name = "idx_title", columnList = "title,category"),
        })

public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;

    private Integer category;

    private Integer type;

    private String title;

    private String choices;

    private String answer;
    /**
     * 是否仅有一个标题图片，如果为true那么titleImg必须有效
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
    /**
     * 原题ID
     */
    private Long cloneFrom;

    private String createTime;

    private String updateTime;

    private Long useTimes = 0L;

    private Long errorTimes = 0L;

    private Integer cloneTimes = 0;

    private Integer favoriteTimes = 0;

    private Integer status = QuestionStatusEnum.VALID.getCode();


}
