package com.test.thread;

/**
 * @author zhouj
 * @since 16/5/13
 */
public class TestThreadHeap {
    public static void main(String[] args) {
        int[] s = new int[1000];
        for (int i=0;i<1000;i++){
            s[i]=i;
        }
        TestHeap testHeap = new TestHeap(s);
        TestHeap1 testHeap1 = new TestHeap1(s);
        Thread thread = new Thread(testHeap,"thread");
        Thread thread1 = new Thread(testHeap1,"thread1");
        thread.start();
        thread1.start();

    }

    public void print(int[] ints){
        for(int i = 0;i<ints.length;i++){
            System.out.println(i);
        }

    }
}


class TestHeap implements Runnable{
    int[] ints;

    public TestHeap(int[] ints){
        this.ints=ints;
    }

    public void run() {
        for(int i =0;i<1000;i++){
            synchronized (ints){ints[i]=i;}
            System.out.println(Thread.currentThread().getName()+":"+ints[i]);
        }
    }
}


class TestHeap1 implements Runnable{
    int[] ints;
    public TestHeap1(int[] ints){
        this.ints=ints;
    }

    public void run() {
        for(int i =0;i<1000;i++){
            System.out.println(Thread.currentThread().getName() + ":" + ints[i]);
        }
    }
}
