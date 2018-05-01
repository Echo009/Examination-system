package cn.ech0.examinationsystem.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/25/2018 01:49 AM
 */
public class DateUtil {
    private static DecimalFormat timeFormat = new DecimalFormat("00");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToStamp(String s) throws ParseException {
        String res;

        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static String stampToDate(String s) {
        String res;
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String formatDate(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDate() {
        return simpleDateFormat.format(new Date());
    }

    /**
     * 计算时间差，小于一天
     * @param beginTime
     * @param endTime
     * @return
     */
    public static String getInterval(Date beginTime, Date endTime) {
        long seconds = (endTime.getTime() - beginTime.getTime()) / 1000;

        long hours = seconds / 3600;
        long mins = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        return timeFormat.format(hours) + ":" + timeFormat.format(mins) + ":" + timeFormat.format(seconds);

    }

}
