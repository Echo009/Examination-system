package cn.ech0.examinationsystem.wrapper;

import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 07:13 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExamPaperWrapper {

    private Long examRecordId;
    /**
     * 试卷id
     */
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
     * 考试时长，以分钟计时，超时自动提交
     */
    private Integer maxExamDuration ;

    private List<QuestionDTO> questions;



}
