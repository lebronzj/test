package com.test.memory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouj
 * @since 2020-05-11
 */
public class TestAtomicIntegerLock {

    private static int synValue;

    public static void main(String[] args) {
        int threadNum = 2;
        int maxValue = 10000000;
        testSync(threadNum, maxValue);
        testLocck(threadNum, maxValue);
    }
    //test Lock
    public static void testSync(int threadNum, int maxValue) {
        Thread[] t = new Thread[threadNum];
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            Lock locks = new ReentrantLock();
            synValue = 0;
            t[i] = new Thread(() -> {
                for (int j = 0; j < maxValue; j++) {
                    locks.lock();
                    try {
                        synValue++;
                    } finally {
                        locks.unlock();
                    }
                }

            });
        }
        for (int i = 0; i < threadNum; i++) {
            t[i].start();
        }
        //main线程等待前面开启的所有线程结束
        for (int i = 0; i < threadNum; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("使用lock所花费的时间为：" + (System.currentTimeMillis() - begin) + "ms");
    }
    // test synchronized
    public static void testLocck(int threadNum, int maxValue) {
        int[] lock = new int[0];
        Long start = System.currentTimeMillis();
        Thread[] t = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            synValue = 0;
            t[i] = new Thread(() -> {
                for (int j = 0; j < maxValue; j++) {
                    synchronized(lock) {
                        ++synValue;
                    }
                }
            });
        }
        for (int i = 0; i < threadNum; i++) {
            t[i].start();
        }
        //main线程等待前面开启的所有线程结束
        for (int i = 0; i < threadNum; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("使用synchronized所花费的时间为：" + (System.currentTimeMillis() - start) + "ms");
    }

    public synchronized void incream(){
        ++synValue;
    }
}
