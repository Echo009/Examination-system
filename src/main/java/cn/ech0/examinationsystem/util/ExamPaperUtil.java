package cn.ech0.examinationsystem.util;

import cn.ech0.examinationsystem.enums.question.BaseCategoryEnum;
import cn.ech0.examinationsystem.enums.question.QuestionTypeEnum;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 03:09 PM
 */
public class ExamPaperUtil {
    /**
     * 将分类转换成字符串的表示方式
     * @param categories
     * @return
     */
    public static String convertoCategoryString(Integer[] categories) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < categories.length; i++) {
            stringBuilder.append(EnumUtil.getByCode(categories[i], BaseCategoryEnum.class).getDesc()+"-");
        }
        return  stringBuilder.substring(0,stringBuilder.lastIndexOf("-"));
    }
    /**
     * 将题目类型转换成字符串的表示方式
     * @param types
     * @return
     */
    public static String convertoTypeString(Integer[] types) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            stringBuilder.append(EnumUtil.getByCode(types[i], QuestionTypeEnum.class).getDesc()+"-");
        }

        return stringBuilder.substring(0,stringBuilder.lastIndexOf("-"));
    }

}
