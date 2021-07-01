package com.test.thread;

/**
 * volatile实现交替打印
 *
 * @author zhouj
 * @since 2021-01-11
 */
class A {

    public static Boolean symbol = true;

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (true) {
                if (symbol) {
                    System.out.println("A");
                    symbol = false;
                }
            }
        });

        Thread b = new Thread(() -> {
            while (true) {
                if (!symbol) {
                    System.out.println("B");
                    symbol = true;
                }
            }
        });
        a.start();
        b.start();
    }
}

