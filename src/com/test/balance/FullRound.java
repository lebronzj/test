package com.test.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 完全轮询
 *
 * @author zhouj
 * @since 2020-08-14
 */

public class FullRound {

    public static List<String> list = new ArrayList() {
        {
            add("192.168.1.1");
            add("192.168.1.2");
            add("192.168.1.3");
        }
    };

    static AtomicInteger index;

    public static String go() {
        if (index.equals(new AtomicInteger(list.size()))) {
            index = new AtomicInteger(0);
        }
        return list.get(index.incrementAndGet());
    }


    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }
}
