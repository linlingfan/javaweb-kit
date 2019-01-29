package com.glinlf.growth.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * @author com.glinlf
 * @create 2016-12-30 下午6:00
 */
public class DateKit {

    public static final String LONG_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    public static final String LONG_TEMPLATE_HM = "yyyy-MM-dd HH:mm";

    public static final String LONG_TEMPLATE_HH = "yyyy-MM-dd HH";

    public static final String SHORT_TEMPLATE = "yyyy-MM-dd";

    public static final String MONTH_TEMPLATE = "yyyy-MM";

    public static final String TIME_TEMPLATE = "HH:mm:ss";

    public static final String TIME_TEMPLATE_HM = "HH:mm";

    /**
     * 生成 UUID
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }


    /**
     * 获取当前日期时间
     * 格式: yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String nowLong() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

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
     * 获取当天日期
     *
     * @return
     */
    public static String today() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * 获取当天日期时间串
     *
     * @return
     */
    public static String today4number() {
        return today().replace("-", "");
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 时间戳转格式化日期字符串
     * java.sql.Timestamp -> String
     *
     * @param ts
     * @return
     */
    public static String ts2Str(Timestamp ts) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(ts);
    }

    /**
     * 字符串转数据库时间戳
     * String -> java.sql.Timestamp
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Timestamp str2Ts(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(str);
        return new Timestamp(date.getTime());
    }

    public static Timestamp strDay2Ts(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getTime() {
        return new Date().getTime();
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    //***********************change date ***********************

    /**
     * 获取当前时间加几天后的日期
     *
     * @return
     */
    public static Date changeTimeByDay_date(int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, dayNum);
        return calendar.getTime();
    }

    public static Date changeTimeByDay_date(Date inputDate, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DATE, dayNum);
        return calendar.getTime();
    }

    /**
     * 通过天数改变时间 返回格式化后的时间
     *
     * @param inputDate 日期
     * @param dayNum    天数
     * @param template  模板
     * @return
     */
    public static String changeDateByInt_str(Date inputDate, int dayNum, String template) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DATE, dayNum);

        return dateFormatStr(template, calendar.getTime());
    }

    /**
     * change date by days
     *
     * @param inputDate date
     * @param dayNum    date number
     * @return
     */
    public static Date changeDateByInt_date(Date inputDate, int dayNum) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(Calendar.DATE, dayNum);

        return calendar.getTime();
    }

    /**
     * change date by  millisseconde return date
     *
     * @param inputDate date
     * @param ms        millisseconde
     * @return
     */
    public static Date changeDateByLong_date(Date inputDate, long ms) {
        long newMillis = inputDate.getTime() + ms;
        return new Date(newMillis);
    }

    /**
     * change date by  millisseconde return String
     *
     * @param inputDate
     * @param ms
     * @param template
     * @return
     */
    public static String changeDateByLong_date(Date inputDate, long ms, String template) {
        long newMillis = inputDate.getTime() + ms;
        return dateFormatStr(template, new Date(newMillis));
    }

    // ************** 格式化时间 ***************

    /**
     * 时间格式化
     *
     * @param template 模板
     * @param date
     * @return
     */
    public static String dateFormatStr(String template, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(template);
        return formatter.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param template
     * @param date
     * @return
     */
    public static Date strFormatDate(String template, String date) throws ParseException {
        return new SimpleDateFormat(template).parse(date);
    }

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

    // --------------------------- change end------------------------


    public static String tsLong2short(Timestamp ts) throws ParseException {
        SimpleDateFormat longFormatter = new SimpleDateFormat(LONG_TEMPLATE);
        SimpleDateFormat shortFormatter = new SimpleDateFormat(SHORT_TEMPLATE);
        Date d = longFormatter.parse(ts2Str(ts));
        return shortFormatter.format(d);
    }

    /**
     * String的cst时间格式的时间转成data类型
     *
     * @param strCstDate
     * @return
     */
    public static Date strCstToDate(String strCstDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = null;
        try {
            date = sdf.parse(strCstDate);
        } catch (ParseException e) {
            e.getMessage();
        }
        return date;
    }

    public static Date strUtcToDate(String strUTC) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            return format.parse(strUTC.replace("Z", " UTC"));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return new Date(); // 返回当前时间
        }
    }

    /**
     * 获得10位 unix时间戳
     *
     * @return
     */
    public static Long getUnixTime() {
        return Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(0, 10));
    }

    /**
     * date类型转换成unix时间戳
     *
     * @return
     */
    public static Long dataToUnixTime(Date date) {
        if (date == null) {
            return -1L;
        }

        return Long.valueOf(String.valueOf(date.getTime()).substring(0, 10));
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
     * 给定日期模板获取格式化工厂
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

    public static Date tt(String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        return format.parse(str);
    }


    /**
     * 获取时间年份的第一天 xx年 1月01日
     *
     * @param date
     * @return
     */
    public static Date getYearsFirstDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        return calendar.getTime(); // 时间年份第一天;
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
//        calendar.set(Calendar.MILLISECOND, 999);
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

    public static boolean isToday(Date date) {
        return isSameTime(date, "yyyy-MM-dd");
    }

    public static boolean isSameTime(Date d, String pattern) {
        SimpleDateFormat sdf = formatter(pattern);
        String param = sdf.format(d);
        String now = sdf.format(now());
        return param.equals(now);
    }
}
