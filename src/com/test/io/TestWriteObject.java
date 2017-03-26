package com.test.io;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhouj
 * @since 16/6/14
 */
public class TestWriteObject {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date startTime = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date endTime = calendar.getTime();
        System.out.println(startTime);
        System.out.println(endTime);
    }
}
