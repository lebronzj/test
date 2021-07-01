package com.test.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhouj
 * @since 2020-08-14
 */
public class FullRandom {

    public static List<String> list = new ArrayList() {
        {
            add("192.168.1.1");
            add("192.168.1.2");
            add("192.168.1.3");
        }
    };

    static Random random = new Random();

    public  static String  go() {
        int number = random.nextInt(list.size());
        return list.get(number);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }
}
