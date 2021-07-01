package com.test.volatiles;

/**
 * @author zhouj
 * @since 2020-06-04
 */
class A {
    int a = 0;
    boolean flag = false;

    public void testA() {
        flag = false;
        //语句1
        a = 1;
        //语句2
        flag = true;
    }

    public void testB() {
        if (flag) {
            a = a + 5;
            if(a==5){
                System.out.println(a);
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        for (int i = 0; i < 5000000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.testA();
                }
            }, "A").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.testB();
                }
            }, "B").start();
        }

    }
}
