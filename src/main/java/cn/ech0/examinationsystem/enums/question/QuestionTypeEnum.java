package cn.ech0.examinationsystem.enums.question;

import cn.ech0.examinationsystem.enums.CodeEnum;
import lombok.Getter;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/28/2018 10:36 PM
 */
@Getter
public enum  QuestionTypeEnum implements CodeEnum {
    /**
     * 按枚举值决定试题先后顺序
     */

    SINGLE_SELECT(0,"单选"),
    MULTI_SELECT(1,"多选"),
    GAP_FILLING(2,"填空"),
    ASK(3,"问答"),
    CODE(4,"编程"),
    ;

    private Integer code;

    private String desc;

    QuestionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
