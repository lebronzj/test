package com.test.function;

/**
 * @author zhouj
 * @since 2021-04-23
 */
public class FunctionMain {
    public static void main(String[] args) {
        FunctionTest functionTest = ()-> System.out.println("test");
        functionTest.test();
    }
}
