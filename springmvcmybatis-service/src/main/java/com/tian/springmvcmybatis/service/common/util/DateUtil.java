package com.tian.springmvcmybatis.service.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用的时间工具类
 * Created by Administrator on 2016/12/16 0016.
 */
public class DateUtil {
    /**
     * 获取给定时间前的N小时的时间
     * @param date 给定的时候
     * @param number 要添加的小时数
     * @return
     */
    public static Date getDateBeforeHour(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,number);
        return calendar.getTime();
    }

    /**
     * 获取当前时间前的N小时的时间
     * @param number
     * @return
     */
    public static Date getDateBeforeHour(int number){
        return getDateBeforeHour(new Date(),number);
    }

    /**
     * 返回给定的时间前N小时的时间字符串
     * @param date 时间
     * @param number 时间位移度
     * @param pattern 格式化的模式
     * @return
     */
    public static String getDateBeforeHour(Date date, int number,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getDateBeforeHour(date,number));
    }

    /**
     * 获取当前时间前N小时的指定格式化的时间串
     * @param number 时间偏移量
     * @param pattern 格式
     * @return
     */
    public static String getDateBeforeHour(int number,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getDateBeforeHour(new Date(),number));
    }
}
