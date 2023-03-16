package com.test.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zhouj
 * @since 2021-07-20
 */
public class TestSycronize {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        A a1 = new A(o);
        a1.start();
    }

    public static class A extends Thread {

        private Object object;

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("test a1:" + ClassLayout.parseInstance(object).toPrintable());
            }
        }

        public A(Object o) {
            this.object = 0;
        }
    }
}
