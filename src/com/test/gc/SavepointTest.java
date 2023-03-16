package com.test.gc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * JIT优化for循环中的循环末尾安全点检查
 * @author zhouj
 * @since 2022-05-15
 */
public class SavepointTest {

    public static AtomicInteger num = new AtomicInteger(0);
//    public volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=()->{
            for (int i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
//                num++;
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }
}
