package com.test.calculate;

import java.util.List;

/**
 * Created by zhouj on 16/4/6.
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        //输出ClassLoaderText的类加载器名称
        System.out.println("ClassLoaderText类的加载器的名称:"+ClassLoadTest.class.getClassLoader().getClass().getName());
        System.out.println("System类的加载器的名称:"+System.class.getClassLoader());
        System.out.println("List类的加载器的名称:"+List.class.getClassLoader());

        ClassLoader cl = ClassLoadTest.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);
        System.out.println("当前线程类加载器:"+Thread.currentThread().getContextClassLoader());
    }
}
