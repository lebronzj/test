package com.test.thread;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther zhouj
 * @since 2017/8/21
 */
public class TestDeadLock implements Runnable {

    public boolean flag;

    public Integer a;

    public Integer b;



    public TestDeadLock(boolean flag,Integer a, Integer b){
        this.flag = flag;
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        Integer a = new Integer(1);

        Integer b = new Integer(2);
        Thread testDeadLock1 = new Thread(new TestDeadLock(true,a,b));
        Thread testDeadLock2 = new Thread(new TestDeadLock(false,a,b));
        testDeadLock1.start();
        testDeadLock2.start();
    }

    @Override
    public void run() {

        if(flag){
            synchronized (a){
                a= a+1;
                System.out.println(flag+","+a);
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println(flag+","+b);
                }
            }
        }

        if(!flag){
            synchronized (b){
                System.out.println(flag+","+b);
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronized (a){
                    System.out.println(flag+","+a);
                }
            }
        }


    }

}
