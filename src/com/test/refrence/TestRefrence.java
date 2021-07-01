package com.test.refrence;

/**
 * @author zhouj
 * @since 2017/5/19
 */
public class TestRefrence {

    String str = new String("sddssd");

    char[] chars = {'a','b','c'};

    public static void main(String[] args) {
        TestRefrence testRefrence = new TestRefrence();
        testRefrence.change(testRefrence.str,testRefrence.chars);
        System.out.println(testRefrence.str);
        System.out.println(testRefrence.chars.toString());
    }



    public void change(String str,char[] chars){
        str = new String("klkkj");

        chars[0] = 'g';
    }
}
