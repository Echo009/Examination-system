package cn.ech0.examinationsystem.enums.question;

import cn.ech0.examinationsystem.enums.CodeEnum;
import lombok.Getter;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/28/2018 11:27 PM
 */
@Getter
public enum  BaseCategoryEnum implements CodeEnum {


    PROGRAM_LANGUAGE(0,"编程语言"),
    ALGORITHM(1,"算法"),
    DATA_STRUCTURE(2,"数据结构"),
    MATH_LOGIC(3,"数学和逻辑"),
    COMPUTER_BASE(4,"计算机基础"),
    SOFTWARE_DEV(5,"软件开发"),
    COMPOSITE(6, "综合"),
    ;

    private Integer code;

    private String desc;

    BaseCategoryEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
