package com.chrisxyq.spring.learn.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class MyDateUtils {
    public static String calendarToString(Calendar calendar, String pattern) {
        String str = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            str = sdf.format(calendar.getTime());
        } catch (Exception e) {
            log.warn("DateUtils.calendarToString",
                    String.format("calendarToString Exception,pattern:%s,exception:%s",
                            pattern, e.getMessage()), null);
        }
        return str;
    }
    public static Date stringToDate(String str, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(str);
        } catch (ParseException e) {
            log.warn("DateUtils.stringToDate", String.format("stringToDate ParseException,str:%s,pattern:%s,exception:%s", str, pattern, e.getMessage()), null);
        }
        return date;
    }
}
