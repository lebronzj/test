package com.test.volatiles;

/**
 * @author zhouj
 * @since 2020-06-05
 */
public class MainTest {
    static int a =0, b = 0;
    static int x = 0, y = 0;

    public static void func(){
        a = 0; b = 0;
        x = 0; y = 0;
    }

    public static void main(String[] args){

        for(int i = 0; i < 10000; ++i){
            func();

            new Thread(new Runnable(){
                @Override
                public void run(){
                    x = a;
                    a = 1;
                }
            }).start();


            new Thread(new Runnable(){
                @Override
                public void run(){
                    y = b;
                    b = 1;
                }
            }).start();

            System.out.println("x = " + x + " y = " + y);

            if (1 == x && 1 == y) {
                System.out.println("x = " + x + " y = " + y);
                break;
            }
        }
    }
}
