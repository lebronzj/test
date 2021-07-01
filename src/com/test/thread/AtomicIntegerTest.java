package com.test.thread;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhouj on 16/3/24.
 */

public class AtomicIntegerTest {
//    static AtomicInteger value =new AtomicInteger(10);
    static Integer value = 0;
    public static void main (String []args) throws InterruptedException{
//        final AtomicInteger value = new AtomicInteger(10);
//        assertEquals(value.compareAndSet(1, 2), false);
//        assertEquals(value.get(), 10);
//        assertTrue(value.compareAndSet(10, 3));
//        assertEquals(value.get(), 3);
//        value.set(0);
//
//        assertEquals(value.incrementAndGet(), 1);
//        assertEquals(value.getAndAdd(2),1);
//        assertEquals(value.getAndSet(5),3);
//        assertEquals(value.get(),5);
        //
        final int threadSize = 100;
        Thread[] ts = new Thread[threadSize];
        CountDownLatch latch = new CountDownLatch(100);   // 创建倒计时闩并指定倒计时次数为2

        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    for (int i=0;i<1000000;i++){
                        {
                           value++;
//                            value.incrementAndGet();
                        }
                    }
//                    value=value+1;
                }
            };
            ts[i].start();
//            ts[i].join();
//            ts[i].dumpStack();
            ClassLoader loaders = ts[i].getContextClassLoader();
            Map<Thread,StackTraceElement[]> map = ts[i].getAllStackTraces();
            long id = ts[i].getId();
            String name = ts[i].getName();
            int priority = ts[i].getPriority();
//            System.out.println(ts[i].getThreadGroup().getName());
        }
        //
//        for(Thread t:ts) {
//            t.start();
//        }
//        for(Thread t:ts) {
//            t.join();
//        }
//        while (Thread.activeCount()==3)
        System.out.println(value);
        //
//        assertEquals(value.get(), 5+threadSize);
    }

}
