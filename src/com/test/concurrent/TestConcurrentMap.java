package com.test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouj
 * @since 2017/3/27
 */
public class TestConcurrentMap {

    public static Map<String,Object> map = new HashMap();
//    public static Map<String,Object> map = new ConcurrentReaderHashMap();
    public static Integer index;
    public static AtomicInteger dest = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            index=i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int x=0;x<1000;x++){
                        map.put(UUID.randomUUID().toString(),x);
                        dest.getAndIncrement();
                    }
                }
            });
            thread.start();
            try {
                thread.join();
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                System.out.println("groupName:"+threadGroup.getName());
                ThreadGroup threadGroup1 = threadGroup.getParent();
                System.out.println(threadGroup1.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(map.size());
        System.out.println(dest);
    }
}
