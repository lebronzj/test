package com.test.constant;

/**
 * @author zhouj
 * @since 2021-01-11
 */
public class FinalTest {

    public static void main(String[] args) {
        String a = "123";
        String b = "456";
        String c = a+b;
        System.out.println(c=="123456");
//输出：false

//        final String a = "123";
//        final String b = "456";
//        String c = a+b;
//        System.out.println(c=="123456");
//输出：true
    }
}
