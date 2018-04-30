package cn.ech0.examinationsystem.comparetor;

import cn.ech0.examinationsystem.entity.exam.QuestionEntity;

import java.util.Comparator;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 11:32 PM
 *
 * 按题目类型排序
 */
//        SINGLE_SELECT(0,"单选"),
//        MULTI_SELECT(1,"多选"),
//        GAP_FILLING(2,"填空"),
//        ASK(3,"问答"),
//        CODE(4,"编程")

public class QuestionEntityComparator implements Comparator<QuestionEntity> {

    @Override
    public int compare(QuestionEntity o1, QuestionEntity o2) {
        return o1.getType() - o2.getType();
    }
}
