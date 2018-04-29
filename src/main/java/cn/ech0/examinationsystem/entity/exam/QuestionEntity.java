package cn.ech0.examinationsystem.entity.exam;


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
        },
        indexes = {
                @Index(name = "idx_title", columnList = "title,type"),
        })
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;

    private Integer type;

    private String title;

    private String choices;

    private String answer;

    private String titleImg;

    private String createTime;

    private String updateTime;

    private Long useTimes;

    private Long errorTimes;

}
