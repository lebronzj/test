package com.test.memory;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author zhouj
 * @since 2020-07-02
 */
public class ObjectHead {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        System.out.println(GraphLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        int[] aa = new int[0];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println(GraphLayout.parseInstance(obj).totalSize());
    }

    public static class A {}

    public static class B {
        private long s;
    }

    public static class C {
        private int a;
        private long s;
    }

    int[] aa = new int[0];
}
