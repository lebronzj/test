package com.test.jvm;

import java.nio.ByteBuffer;

/**
 * @auther zhouj
 * @since 2017/7/25
 */
public class TestDirect {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            ByteBuffer.allocateDirect(128);
        }
        System.out.println("Done");
    }
}
