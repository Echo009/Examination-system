package cn.ech0.examinationsystem.wrapper;

import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 08:11 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class QuestionWrapper {

    private Integer currentPage;

    private Long totalElements;

    private Integer totalPages;

    private List<QuestionDTO> content;
}
