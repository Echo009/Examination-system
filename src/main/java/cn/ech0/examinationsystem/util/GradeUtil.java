package cn.ech0.examinationsystem.util;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 02:27 PM
 */
@Component
public class GradeUtil {

    private static DecimalFormat gradeFormat = new DecimalFormat("00.00");


    private static DecimalFormat rateFormat = new DecimalFormat("#.##%");


    public static String  formatGrade(double grade) {

        return gradeFormat.format(grade);
    }


    public static String formatRate(double rate){

        return rateFormat.format(rate);

    }

    public static Integer calTotalGrade(Integer[] grades) {
        if (grades == null) {
            return 0;
        }
        int total  = 0;
        for (int i = 0; i < grades.length; i++) {
            total += grades[i];
        }
        return total;
    }

}
