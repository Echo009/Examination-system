package cn.ech0.examinationsystem.entity.exam;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 10:56 AM
 * 试卷
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
public class ExaminationPaperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 创建用户,默认为系统
     */
    private String userId = "System";
    /**
     * 题目数量
     */
    private Integer titleNum;
    /**
     * 试卷包含分类
     */
    private Integer[] categories;
    /**
     * 试卷题型
     */
    private Integer[] types ;
    /**
     * 试卷题目
     */
    private String title;
    /**
     * 试卷题目列表，按题目类型枚举值排列
     */
    private Long[] questions;
    /**
     * 由系统自动生成答案,按试题顺序
     */
    private String[] answers;
    /**
     * 每题的分值
     */
    private Integer[] grades;
    /**
     * 使用次数
     */
    private Integer useTimes = 0;
    /**
     * 平均得分
     */
    private BigDecimal averageGrade = BigDecimal.ZERO;

    private String createTime;
}
