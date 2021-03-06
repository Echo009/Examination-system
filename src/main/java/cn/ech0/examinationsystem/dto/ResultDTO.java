package cn.ech0.examinationsystem.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/16/2018 03:07 PM
 * @author Ech0
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResultDTO<T> {
    public static final ResultDTO BAD_RESULT = new ResultDTO(false, null);

    private Boolean status;
    private T data ;

    public ResultDTO(Boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean isSuccess(){
        return status;
    }

}
