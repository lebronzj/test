package com.test.concurrent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhouj
 * @since 2017/3/25
 */
public class BlockingQueueTest {

    public  BlockingQueueTest(String st){
        System.out.println(st);
    }

    public  void get(){
        synchronized(this){
            System.out.println("sdsdsd");
        }
    }
    public static void main(String[] args) {
        BlockingQueueTest blockingQueueTest = new BlockingQueueTest("sdsdds");
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        try {
            t.join();
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
