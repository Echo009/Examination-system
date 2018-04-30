package cn.ech0.examinationsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/25/2018 01:49 AM
 */
public class DateUtil {

    private static final  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToStamp(String s) throws ParseException {
        String res;

        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static String stampToDate(String s){
        String res;
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String formatDate(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate(){
        return simpleDateFormat.format(new Date());
    }
}
