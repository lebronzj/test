package com.test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouj
 * @since 2017/5/12
 */
public class TestAbstractQueue {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> synchronousQueue = new ArrayBlockingQueue<String>(1000000);
        long time1 = System.currentTimeMillis();
        for(int i = 0;i<1000;i++){
            Thread thread = new Thread(new TestTread(synchronousQueue),"thread"+i);
            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("time2:"+(time1-System.currentTimeMillis()));
        System.out.println("size:"+synchronousQueue.size());
        System.out.println();

    }
}

class TestTread implements Runnable{

    private ArrayBlockingQueue<String> synchronousQueue;

    public TestTread(ArrayBlockingQueue<String> synchronousQueue){
        this.synchronousQueue = synchronousQueue;
    }

    @Override
    public void run() {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        for(int x=0;x<1000;x++){
            String threadName = Thread.currentThread().getName();
            synchronousQueue.add(threadName+x);
        }
        reentrantLock.unlock();

    }
}