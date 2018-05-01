package cn.ech0.examinationsystem.entity.exam;

import cn.ech0.examinationsystem.enums.exam.ExamStatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
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
        name = "eetu_exam_record",
        indexes = {
                @Index(name = "idx_title", columnList = "userId,title"),
        })
public class ExamRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;
    /**
     * 题目数量
     */
    private Integer titleNum;
    /**
     * 试卷包含分类
     * 形如 "计算机网络-操作系统"
     */
    private String categories;
    /**
     * 试卷题型
     * 形如 "计算机网络-操作系统"
     */
    private String types;
    /**
     * 试卷id
     */
    private Long examinationPaperId;
    /**
     * 试卷标题
     */
    private String title ;
    /**
     * 错题序号
     */
    private Integer[] errorQuestionSeqs;
    /**
     * 错题id，对应序号中的顺序
     */
    private Long[] errorQuestions;
    /**
     * 错误答案，对应序号中的顺序
     */
    private String[] errorAnswers;
    /**
     * 得分
     */
    private String grade;
    /**
     * 开始时间
     */
    private Date beginTime = new Date();
    /**
     * 完成时间
     */
    private Date completeTime;
    /**
     * 状态
     */
    private Integer status = ExamStatusEnum.UNFINISHED.getCode();


}
