package com.liangfengyouxin.www.android.frame.utils;

import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017/5/30.
 * 日期工具类
 */
public class DateUtils {
    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FULL_APORPM = "yyyy-MM-dd hh:mma";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyyyMMddHHmmss";
    public static final String DATE_MONTH_DAT_STR = "MM-dd HH:mm";


    /**
     * 将Unix时间戳转换成日期
     */
    public static String unixTimesToDate(long timestamp) {
        return unixTimesToDate(timestamp, DATE_FULL);
    }

    /**
     * 将Unix时间戳转换成日期
     */
    public static String unixTimesToDate(long timestamp, String format) {
        if (TextUtils.isEmpty(format))
            return "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(format);
            return sd.format(new Date(timestamp));
        } catch (Exception e) {
            return "";
        }
    }

    /***
     * 日期转成字符串
     *
     * @param data
     * @param formatType
     * @return
     */

    public static String dateToString(Date data, String formatType) {
        try {
            if (data == null)
                return "";
            else
                return new SimpleDateFormat(formatType).format(data);
        } catch (Exception e) {
            return "";
        }
    }

    /***
     * long类型日期转成string
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /***
     * string转成Date
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /***
     * long转Date
     *
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /***
     * 和当前时间对比
     *
     * @param strTime
     * @param formatType
     * @return
     */
    public static boolean compareDate(String strTime, String formatType) {
        try {
            long targeTime = stringToLong(strTime, formatType);
            long currentTime = new Date().getTime();
            return targeTime > currentTime;
        } catch (ParseException e) {
            return false;
        }
    }

    /***
     * 和当前时间对比
     *
     * @param strTime
     * @return
     */
    public static boolean compareDate(String strTime) {
        try {
            long targeTime = stringToLong(strTime, DATE_FULL);
            long currentTime = new Date().getTime();
            return targeTime > currentTime;
        } catch (ParseException e) {
            return false;
        }
    }

    /***
     * 和当前时间对比
     *
     * @param date
     * @return
     */
    public static boolean compareDate(Date date) {
        try {
            long targeTime = 0;
            if (date == null) {
                targeTime = 0;
            } else {
                targeTime = dateToLong(date); // date类型转成long类型
            }
            long currentTime = new Date().getTime();
            return targeTime > currentTime;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * 获取当前时间
     *
     * @param formatType
     * @return
     */
    public static String currentDate(String formatType) {
        if (TextUtils.isEmpty(formatType))
            return "";
        return new SimpleDateFormat(formatType).format(new Date());
    }

    /***
     * 获取当前时间
     *
     * @return
     */
    public static String currentDate() {
        return currentDate(DATE_FULL);
    }

    public static String strToStr(String dateStr, String sourcePattern, String destPattern) {
        try {
            SimpleDateFormat sourceFormat = new SimpleDateFormat(sourcePattern);
            SimpleDateFormat destFormat = new SimpleDateFormat(destPattern);
            return destFormat.format(sourceFormat.parse(dateStr));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }

    /***
     * 文章评论时间判断
     *
     * @param createDate 2017-01-03 14:21:25
     */
    public static String changeDate(String createDate) {
        if (TextUtils.isEmpty(createDate))
            return "";
        try {
            long mCreateDate = stringToLong(createDate, DATE_FULL);
            long mCurDate = System.currentTimeMillis();

            long minute = (mCurDate - mCreateDate) / 1000 / 60;//换算成分钟
            if (minute >= 0) {
                //分钟
                if (minute < 2) {
                    return "1分钟前";
                } else if (minute <= 59) {
                    return minute + "分钟前";
                }
                //小时
                long hour = minute / 60;//小时数
                if (hour < 2) {
                    return "1小时前";
                } else if (hour <= 23) {
                    return hour + "小时前";
                }

                //天
                long day = hour / 24;//天
                if (day < 2) {
                    return "1天前";
                } else if (day < 30) {
                    return day + "天前";
                }

                //月
                long month = day / 30;
                if (month < 12) {
                    return month + "月前";
                } else {
                    return longToString(mCreateDate, DATE_SMALL_STR);
                }
            } else {
                return longToString(mCreateDate, DATE_SMALL_STR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String appendReviews(String createDate, String appendDate) {
        String result = "";
        if (TextUtils.isEmpty(createDate) || TextUtils.isEmpty(appendDate)) {
            return result;
        }
        try {
            long creteTime = stringToLong(createDate, DATE_FULL);
            long appendTime = stringToLong(appendDate, DATE_FULL);
            long value = appendTime - creteTime;
            long minute = value / 1000 / 60;
            if (minute > 0){
                long hour = minute / 60;
                if (hour > 0){
                    long day = hour / 24;
                    if (day > 0){
                        return day + "天后";
                    } else {
                        return hour + "小时后";
                    }
                } else {
                    return minute + "分钟后";
                }
            } else {
                return "1分钟后";
            }
        } catch (Exception e) {
            return result;
        }
    }
}
