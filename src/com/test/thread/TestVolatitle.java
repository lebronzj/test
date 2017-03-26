package com.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouj
 * @since 16/9/14
 */
public class TestVolatitle {

    public static AtomicInteger race = new AtomicInteger(0);
//    public static volatile Integer race = 10;

    public static  void  increase(){
        race.incrementAndGet();
//        race++;
    }

    private static final int THREADS_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];

        for(int i = 0;i<THREADS_COUNT;i++){
            threads[i] = new Thread(()->{
                for (int j=0;j<10000000;j++){
                    {
                        increase();
                    }
                }
            });
            threads[i].start();
//            threads[i].join();
            //等待所有线程都结束
//            while (Thread.activeCount()>1){
//                Thread.yield();
//                System.out.println("ThreadName:"+Thread.currentThread().getName());
//            }
            System.out.println("线程活跃数:"+Thread.activeCount());
            System.out.println(race);
        }

//        for(Thread t:threads) {
//            t.start();
//        }
        for(Thread thread:threads){
            thread.join();
        }
        System.out.println(race);
    }
}
