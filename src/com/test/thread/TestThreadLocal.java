package com.test.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouj
 * @since 2017/3/26
 */
public class TestThreadLocal {
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 45;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i,0)).start();
        }
//        System.out.println(value.get());
    }
    static class MyThread implements Runnable {
        private int index;
        public int x;
        public MyThread(int index,int x) {
            this.index = index;
            this.x = x;
        }
        public void run() {
//            System.out.println("线程" + index + "的初始value:" + value.get());
            Lock lock = new ReentrantLock();
            for (int i = 0; i < 10; i++) {
                if(i==3){
                    System.out.println("线程前" + index+"计算中的value:"+value.get());
                }
                lock.lock();
                x+=i;
//                value.set(value.get() + i);
                Thread thread = Thread.currentThread();
                if(i==3){
                    System.out.println("线程后" + index+"计算中的value:"+value.get());
//                    System.out.println("线程后" + index+"计算中的value:"+value);
                }
            }
            System.out.println(x);
//            System.out.println("线程" + index + "的累加value:" + value.get());
        }
    }
}
