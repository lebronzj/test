package com.test.volatiles;

/**
 * 这段代码很好的展示了volatile的作用
 *
 * @author zhouj
 * @since 2020/1/9
 */

public class ThreadTest {

    private static C c = new C();

    public static void main(String[] args) {


        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (c.x == 'A') {
                        System.out.println('A');
                        c.setX('B');
                    } else {
                        //System.out.println("aaaaaa===="+c.x);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (c.x == 'B') {
                        System.out.println('B');
                        c.setX('C');
                    } else {
                        //System.out.println("bbbbbbbb===="+c.x);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (c.x == 'C') {
                        System.out.println('C');
                        c.setX('A');
                    } else {
                        //System.out.println("ccccccc===="+c.x);
                    }
                }
            }
        }).start();


    }


}


class C {
    public char x = 'A';

    public void setX(char a) {
        x = a;
    }
}