package cn.ech0.examinationsystem.structure;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 10:01 AM
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProgramTitle {

    private String title;

    private String detail;

}
