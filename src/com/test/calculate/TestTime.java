package com.test.calculate;

import java.util.Calendar;

/**
 * Created by zhouj on 16/4/9.
 */
public class TestTime {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-1);
        System.out.println(calendar.getTime().getTime()>System.currentTimeMillis());
    }
}
