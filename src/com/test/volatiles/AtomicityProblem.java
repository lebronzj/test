package com.test.volatiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouj
 * @since 2020-06-12
 */
public class AtomicityProblem {

    /***共享变量*/
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {

        Runnable increment = () -> {
            for (int i = 0; i < 1000; i++) {
                num++;
            }
        };

        List<Thread> threads = new ArrayList<>();

        //10个线程各执行1000次num++
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(increment);
            t.start();
            threads.add(t);
        }

        //确保100个线程都走完
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("100个线程执行后的结果为:" + num);
    }
}

