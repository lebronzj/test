package com.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouj
 * @since 2017/4/6
 */
public class ReentrantLockTest {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock(true);
        try {
            lock.tryLock();
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }
}
