package com.test.tryfinl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import sun.misc.Unsafe;

/**
 * @auther zhouj
 * @since 2017/7/25
 */
public class TestTryReturn {
    public static void main(String[] args) {
        System.out.println(new TestTryReturn().findTest());
    }


    public String findTest(){
        String s = "world";
        Map<String,String> map = new HashMap<>();
        map.put("s","world");
        try {
            Constructor constructor = Unsafe.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            try {
                Unsafe unsafe = (Unsafe) constructor.newInstance();
                System.out.println(unsafe.addressSize());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            unsafe.setAccessible(true);
            try {
                Unsafe unsafe1 = (Unsafe)unsafe.get(Unsafe.class);
                System.out.println(unsafe1.addressSize());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            return map.get("s");
        }catch (Exception e){

        }
        finally {
            s="hello";
            map.put("s","hello");
            System.out.println("e:"+s);
        }
        return s;

    }
}
