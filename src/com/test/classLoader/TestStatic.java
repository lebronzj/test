package com.test.classLoader;

import com.test.arithmetic.StaticClass;

/**
 * @author zhouj
 * @since 2017/5/23
 */
public class TestStatic {


    {
        System.out.println("llk");
    }
    {
        System.out.println("sdjsdjsd");
    }

    public static void main(String[] args) {
        TestStatic testStatic = new TestStatic();
        testStatic.get();
    }

    public void get(){
//        StaticClass staticClass;
        StaticClass.print();
    }
}


class Zhou{
    static {
        System.out.println("zhou");

    }
}
