package com.test.time;

import java.util.Date;

/**
 * @author zhouj
 * @since 16/5/9
 */
public class TestsTimeMi {
    public static void main(String[] args) {
        Long t = 1462093065000l;
        int year = 1000*60*60*24*365;
        int mo = 1000*60*60*24*30;
        int dayt = 1000*60*60*24;
        int hour = 1000*60*60;
        int mini = 1000*60;
        System.out.println(t%year+"年"+(t%mo)%year);
        System.out.println(new Date(t));
    }
}
