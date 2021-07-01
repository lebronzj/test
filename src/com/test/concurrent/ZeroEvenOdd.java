package com.test.concurrent;


import java.util.function.IntConsumer;

/**
 * @author zhouj
 * @since 2020-07-07
 */
public class ZeroEvenOdd {

    public static void main(String[] args) throws ClassNotFoundException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(100);
        Thread thread1 = new Thread(new A(zeroEvenOdd),"A");
        Thread thread2 = new Thread(new B(zeroEvenOdd),"B");
        Thread thread3 = new Thread(new C(zeroEvenOdd),"C");
        thread1.start();
        thread2.start();
        thread3.start();
        Class.forName("com.test.concurrent.ConTest");
        System.out.println(Thread.currentThread().getContextClassLoader());
        for(;;){}
    }

    public static class Test{
        static {
            System.out.println("static");
        }

        public static String s="weew";
    }

    public static class A implements Runnable {
        private ZeroEvenOdd zeroEvenOdd;

        private Thread thread;

        public A(ZeroEvenOdd zeroEvenOdd) {
            this.zeroEvenOdd = zeroEvenOdd;
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                zeroEvenOdd.zero(value -> System.out.print(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class B implements Runnable {
        private ZeroEvenOdd zeroEvenOdd;

        public B(ZeroEvenOdd zeroEvenOdd) {
            this.zeroEvenOdd = zeroEvenOdd;
        }

        @Override
        public void run() {
            try {
                zeroEvenOdd.even(value -> System.out.print(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class C implements Runnable {
        private ZeroEvenOdd zeroEvenOdd;

        public C(ZeroEvenOdd zeroEvenOdd) {
            this.zeroEvenOdd = zeroEvenOdd;
        }

        @Override
        public void run() {
            try {
                zeroEvenOdd.odd(value -> System.out.print(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    Integer x = 0;

    Integer i = 0;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (i<n) {
                if (x == 0) {
                    printNumber.accept(0);
                }
                if (i % 2 == 0) {
                    x = 1;
                } else {
                    x = 2;
                }
                this.notifyAll();
                this.wait();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        synchronized (this) {
            while (i<n) {
                if (x == 1) {
                    i++;
                    printNumber.accept(i);
                    x = 0;
                }
                this.notifyAll();
                if(i<n){
                    this.wait();
                }            }
            return;

        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (i<n) {
                if (x == 2) {
                    i++;
                    printNumber.accept(i);
                    x = 0;

                }
                this.notifyAll();
                if(i<n){
                    this.wait();
                }
            }
        }
    }
}
