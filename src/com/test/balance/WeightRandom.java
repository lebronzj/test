package com.test.balance;

import java.util.*;

/**
 *
 * 加权随机
 *
 *
 * @author zhouj
 * @since 2020-08-14
 */
public class WeightRandom {

    public static HashMap<String, Integer> map = new HashMap() {
        {
            put("192.168.1.1", 2);
            put("192.168.1.2", 7);
            put("192.168.1.3", 1);
        }
    };

    static Random random = new Random();

    public static String go() {
        int allWeight = map.values().stream().mapToInt(a -> a).sum();
        int number = random.nextInt(allWeight);
        for (Map.Entry item : map.entrySet()) {
            if ((Integer)item.getValue() >= number) {
                return (String) item.getKey();
            }
            number -= (Integer)item.getValue();
        }
        return "";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }
}
