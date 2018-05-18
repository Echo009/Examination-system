package cn.ech0.examinationsystem.enums.exam;

import cn.ech0.examinationsystem.enums.CodeEnum;
import lombok.Getter;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/17/2018 12:25 PM
 */
@Getter
public enum AuditStatusEnum implements CodeEnum {
    WAIT_FOR_AUDIT(0,"WAIT FOR AUDIT!"),
    APPROVED(1,"APPROVED"),

    ;
    private Integer code;
    private String desc;


    AuditStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public Integer getCode() {
        return code;
    }
}
