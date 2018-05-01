package cn.ech0.examinationsystem.comparetor;

import cn.ech0.examinationsystem.request.AnswerItem;

import java.util.Comparator;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 09:13 AM
 * 按题目序号排序
 */

public class AnswerItemComparator implements Comparator<AnswerItem>{

    @Override
    public int compare(AnswerItem o1, AnswerItem o2) {
        return o1.getSeq()-o2.getSeq();
    }
}
