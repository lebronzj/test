package com.test.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 揪出一个导致GC慢慢变长的JVM设计缺陷
 *
 *
 *
 * jvm: -Xmx300M -Xms300M -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintReferenceGC
 *
 * @author zhouj
 * @since 2019/12/13
 */
public class MHTest {

    static MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static void main(String args[]){

        while(true){

            MethodType type = MethodType.methodType(double.class, double.class);

            try {

                MethodHandle mh = lookup.findStatic(Math.class, "log", type);

            } catch (NoSuchMethodException e) {

                e.printStackTrace();

            } catch (IllegalAccessException e) {

                e.printStackTrace();

            }

        }

    }

}


