package com.test.time;

/**
 * @author zhouj
 * @since 16/10/13
 */
public class TestBinary {
    public static void main(String[] args) {
        String string = "九月工资还没发";
        char[] chars = string.toCharArray();
        for (int i =0;i<chars.length;i++){
            System.out.println(Integer.toBinaryString(chars[i]));
        }
    }
}
