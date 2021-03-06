package cn.ech0.examinationsystem.dto.exam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 03:54 PM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExaminationPaperDTO {

    private Long id;
    /**
     * 创建用户,默认为系统
     */
    private String creatorId ;

    private String creatorName;
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
    private Integer[] types;
    /**
     * 试卷题目
     */
    private String title;
    /**
     * 试卷题目列表，按题目类型枚举值排列
     */
    private Long[] questions;
    /**
     * 每题的分值
     */
    private Integer[] grades;
    /**
     * 考试时长，以分钟计时，超时自动提交
     */
    private Integer maxExamDuration ;
    /**
     * 使用次数
     */
    private Integer useTimes;
    /**
     * 错误次数
     */
    private Integer errorTimes;
    /**
     * 平均得分
     */
    private BigDecimal averageGrade ;

    private String createTime;


}
