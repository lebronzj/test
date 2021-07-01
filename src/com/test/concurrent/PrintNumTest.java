package com.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhouj
 * @since 2020-07-07
 */
public class PrintNumTest {
    static AtomicInteger num = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {

        Print p1 = new Print();
        Print p2 = new Print();
        Print p3 = new Print();
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        t1.setName("thread-cc-1");
        t2.setName("thread-cc-2");
        t3.setName("thread-cc-3");
        p1.setT(t2);
        p2.setT(t3);
        p3.setT(t1);
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }

    static class Print implements Runnable {
        private volatile Thread t;

        @Override
        public void run() {
            while (true) {
                LockSupport.park();
                if (num.get() > 100) {
                    LockSupport.unpark(t);
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " : " + num.getAndIncrement());
                LockSupport.unpark(t);
            }
        }

        public void setT(Thread t) {
            this.t = t;
        }
    }

}
