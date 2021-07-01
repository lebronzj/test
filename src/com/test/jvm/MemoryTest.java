package com.test.jvm;

/**
 * @author zhouj
 * @since 2017/4/11
 */
public class MemoryTest {

    /**
     * -verbose:gc -Xms20K -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     */
    public String name = "zhou";

    public static void main(String[] args) {
        byte[] bytes = new byte[1024*1024*5];
        byte[] bytes1 = new byte[1024*1024*5];
        byte[] bytes2 = new byte[1024*1024*2];
        byte[] bytes3 = new byte[1024*1024*2];
        byte[] bytes4 = new byte[1024*1024*2];
        byte[] bytes5 = new byte[1024*1024*2];
        System.out.println("dssdsdsd");
        System.out.println(args);
        System.out.println(bytes.length);
        System.out.println(bytes1.length);
        System.out.println(bytes5.length);
    }
}
