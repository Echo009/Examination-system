package cn.ech0.examinationsystem.structure;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 09:46 AM
 */

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Choice {

    private String A ;

    private String B ;

    private String C ;

    private String D ;

    private String E ;

    private String F ;

}
