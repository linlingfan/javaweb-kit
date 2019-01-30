package com.glinlf.growth.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author glinlf
 * @create 2016-12-30 下午6:00
 * update 2018-09-05
 */
public class DateKit {

    public static final String LONG_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    public static final String LONG_TEMPLATE_HM = "yyyy-MM-dd HH:mm";

    public static final String LONG_TEMPLATE_HH = "yyyy-MM-dd HH";

    public static final String SHORT_TEMPLATE = "yyyy-MM-dd";

    public static final String MONTH_TEMPLATE = "yyyy-MM";

    public static final String TIME_TEMPLATE = "HH:mm:ss";

    public static final String TIME_TEMPLATE_HM = "HH:mm";

    public static final String YEAR = "YYYY";

    public static final String CST_TEMPLATE = "EEE MMM dd HH:mm:ss zzz yyyy";


    /**
     * 获取当天凌晨时间 00:00:000
     *
     * @return
     */
    public static Date weeHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 给定日期模板获取格式化
     *
     * @param template
     * @return
     */
    public static SimpleDateFormat formatter(String template) {
        return new SimpleDateFormat(template);
    }

    /**
     * 对给定日期根据模板进行格式化
     *
     * @param d
     * @param template
     * @return
     */
    public static String format(Date d, String template) {
        return formatter(template).format(d);
    }


    /**
     * 格式化时间-Local.China
     *
     * @param date
     * @return
     */
    public static String formatDateLocalChina(Date date, String template) {
        SimpleDateFormat formatter = new SimpleDateFormat(template, Locale.CHINA);
        return formatter.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param template
     * @param date
     * @return
     */
    public static Date format(String date, String template) throws ParseException {
        return new SimpleDateFormat(template).parse(date);
    }


    /**
     * String的cst时间格式的时间转成data类型
     * Locale.US
     *
     * @param strCstDate
     * @return
     */
    public static Date format(String strCstDate, String template, Locale locale) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(template, locale);
        Date date = sdf.parse(strCstDate);
        return date;
    }

    //***********************change date ***********************

    /**
     * 加月数
     *
     * @param date
     * @param safeMonthCheckNotRate
     * @return
     */
    public static Date addMonth(Date date, Integer safeMonthCheckNotRate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, safeMonthCheckNotRate);
        return calendar.getTime();
    }

    /**
     * 添加日期
     *
     * @param date
     * @param safeDayCheckNotRate
     * @return
     */
    public static Date addDay(Date date, Integer safeDayCheckNotRate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, safeDayCheckNotRate);
        return calendar.getTime();
    }

    /**
     * 加小时 24小时制
     *
     * @param date
     * @param safeSecondCheckNotRate
     * @return
     */
    public static Date addHour(Date date, Integer safeSecondCheckNotRate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, safeSecondCheckNotRate);
        return calendar.getTime();
    }

    /**
     * 加分钟数
     *
     * @param date
     * @param safeMinCheckNotRate
     * @return
     */
    public static Date addMin(Date date, Integer safeMinCheckNotRate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, safeMinCheckNotRate);
        return calendar.getTime();
    }

    /**
     * 加秒数
     *
     * @param date
     * @param safeSecondCheckNotRate
     * @return
     */
    public static Date addSecond(Date date, Integer safeSecondCheckNotRate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, safeSecondCheckNotRate);
        return calendar.getTime();
    }


    /**
     * change date by  millisseconde return date
     *
     * @param inputDate date
     * @param ms        millisseconde
     * @return
     */
    public static Date addMS(Date inputDate, long ms) {
        long newMillis = inputDate.getTime() + ms;
        return new Date(newMillis);
    }

    /**
     * 设置时间为当天最后一秒 23:59:59:999
     *
     * @param date
     * @return
     */
    public static Date setDayLastSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 设置时间为当天整点 00:00:00:000
     *
     * @param date
     * @return
     */
    public static Date setDayBeginSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // --------------------------- change end------------------------

    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 得到本月的最后一天
     * 计算时间范围要加一天（理论为下个月的零点）
     *
     * @return
     */
    public static Date getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 判断时间是否在一定的范围内
     *
     * @param date1
     * @param date2
     * @param rangeTime 前后相隔的秒数
     * @return
     */
    public static boolean inRange(Date date1, Date date2, int rangeTime) {
        return (Math.abs(date1.getTime() - date2.getTime()) / 1000) < rangeTime;
    }

    /**
     * 判断时间是否在一定的范围内(正序)
     *
     * @param lastTime
     * @param newTime
     * @param rangeTime 前后相隔的秒数
     * @return
     */
    public static boolean inRangePositive(Date lastTime, Date newTime, int rangeTime) {
        return ((newTime.getTime() - lastTime.getTime()) / 1000) < rangeTime;
    }

    /**
     * 相隔的天数 向前取整
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return
     */
    public static Integer diffDay(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return null;
        return (int) Math.ceil((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24.0));
    }

    /**
     * 每个月的总天数（最后一天）
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        // 某年某月的最后一天
        return cal.getActualMaximum(Calendar.DATE);
    }


    /**
     * 获取时间年份的第一天 xx年 1月01日
     *
     * @param date
     * @return
     */
    public static Date yearsFirstDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        return calendar.getTime(); // 时间年份第一天;
    }

    /**
     * 某年的最后一天
     *
     * @param date
     * @return
     */
    public static Date yearsLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), 11, 31, 0, 0, 0);
        return calendar.getTime(); // 时间年份第一天;
    }

    /**
     * 是否为今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        return isSameTime(date, new Date(), DateKit.SHORT_TEMPLATE);
    }

    /**
     * 比较时间
     *
     * @param d
     * @param pattern
     * @return
     */
    public static boolean isSameTime(Date dateOne, Date dateTwo, String pattern) {
        SimpleDateFormat sdf = formatter(pattern);
        String arg1 = sdf.format(dateOne);
        String arg2 = sdf.format(dateTwo);
        return arg1.equals(arg2);
    }
}
