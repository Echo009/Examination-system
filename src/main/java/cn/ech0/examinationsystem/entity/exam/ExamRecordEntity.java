package cn.ech0.examinationsystem.entity.exam;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 12:48 PM
 * 考试记录
 */
@Entity
@Data
@Table(
        indexes = {
                @Index(name = "idx_title", columnList = "userId,type,title"),
        })
public class ExamRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;
    /**
     * 试卷类型
     */
    private Integer type;
    /**
     * 试卷id
     */
    private Long examinationPaperId;
    /**
     * 试卷标题
     */
    private Long title ;

    private String errorQuestions;
    /**
     * 得分
     */
    private String grade;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 完成时间
     */
    private Date completeTime;
    /**
     * 状态
     */
    private Integer status;


}
