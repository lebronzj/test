package com.test.classLoader;

/**
 * @author zhouj
 * @since 2021-07-19
 */
public class TestClassLoader {

    public static void main(String[] args) {
//        System.out.println("bootstrapClassLoader:"+System.getProperty("sun.boot.class.path"));
        System.out.println("extClassLoader:"+System.getProperty("java.class.path"));
//        System.out.println("appClassLoader:"+System.getProperty("java.ext.dirs"));
    }
}

