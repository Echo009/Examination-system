package cn.ech0.examinationsystem.enums.exam;

import cn.ech0.examinationsystem.enums.CodeEnum;
import lombok.Getter;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 09:32 AM
 */

@Getter
public enum ExamStatusEnum implements CodeEnum {
    UNFINISHED(0,"unfinished!"),
    FINISHED(1,"finished"),

    ;
    private Integer code;
    private String desc;


    ExamStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public Integer getCode() {
        return code;
    }
}
