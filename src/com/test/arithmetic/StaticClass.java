package com.test.arithmetic;

/**
 * @author zhouj
 * @since 2017/5/23
 */
public class StaticClass {
    static {
        System.out.println("staticClass");
    }

    public static void print(){

        int a = 0;
        int b = 1;
        switch (a){
            case 3:
                System.out.println(a);
                break;
            case 2:
                System.out.println(b);
        }

    }
}
