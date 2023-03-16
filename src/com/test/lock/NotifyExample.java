package com.test.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouj
 * @since 2023-01-31
 */
public class NotifyExample {

    // 保存休眠线程的顺序
    private static List<String> waitList = new ArrayList<>();
    // 保存唤醒线程的顺序
    private static List<String> notifyList = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        // 休眠 100 个线程
        for (int i = 0; i < 100; i++) {
            String threadName = Integer.toString(i); // 定义线程名
            new Thread(() -> {
                // 获取当前执行线程的线程名
                String currThreadName = Thread.currentThread().getName();
                synchronized (lock) {
                    waitList.add(currThreadName); // 存入等待 list
                    try {
                        lock.wait(); // 休眠线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notifyList.add(currThreadName); // 存储唤醒 list
                }
            }, threadName).start();
        }
        Thread.sleep(1000);
        // 唤醒 100 个线程
        for (int i = 0; i < 100; i++) {
            synchronized (lock) {
                lock.notify(); // 唤醒线程
            }
        }
        // 打印 2 个线程列表
        System.out.println("等待线程顺序：" + waitList);
        System.out.println("唤醒线程顺序：" + waitList);
    }
}
