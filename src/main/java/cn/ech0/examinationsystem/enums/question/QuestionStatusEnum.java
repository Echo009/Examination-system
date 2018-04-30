package cn.ech0.examinationsystem.enums.question;

import cn.ech0.examinationsystem.enums.CodeEnum;
import lombok.Getter;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 12:37 PM
 */
@Getter
public enum QuestionStatusEnum implements CodeEnum {

    VALID(0, "有效"),
    DELETED(1, "已删除"),
    ;
    private Integer code;
    private String desc;

    QuestionStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
