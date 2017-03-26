package com.test.reflect;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author zhouj
 * @since 16/5/10
 */
@SpringBootApplication
public class TestReflect {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class c = Integer.class;
        Class sc = TestReflect.class;
        Method[] methods = sc.getMethods();
        for(Method method:methods){
//            System.out.println(method.getName());
//            System.out.println(method.getModifiers());
        }
        Method method = c.getMethod("toBinaryString",int.class);
        System.out.println(method.getName());
        System.out.println(method.getModifiers());
        System.out.println(Modifier.isFinal(method.getModifiers()));
        System.out.println(Modifier.isPublic(method.getModifiers()));
        System.out.println(Modifier.isStatic(method.getModifiers()));
        System.out.println(Modifier.FINAL);
        System.out.println(Modifier.ABSTRACT);

        Field[] fields = c.getFields();
//        Field field = c.getField("");

        Package pack = c.getPackage();
        System.out.println(pack.getName());
        System.out.println(pack.hashCode());
        System.out.println(pack.getAnnotations());
//        c.newInstance();


        Class stringArr = String[].class;
        System.out.println(stringArr.getName());
    }
}

