package com.test.calculate;

import com.test.proxy.BookFacade;
import com.test.proxy.BookFacadeImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * Created by zhouj on 16/3/25.
 */
public class 位移 {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        int s =2;
        System.out.println(s>>>1);

        //异或
        int m = 6;
        int n = m^112;
        System.out.println(n);
        int l = n^112;
        System.out.println(l);

        System.out.println();
        byte[] ch = "姐".getBytes();
        for (int i =0;i<"周杰".length();i++){
            System.out.println("周杰".charAt(i));
        }
        for(int i =0;i<ch.length;i++){
            System.out.println(Integer.toBinaryString((Integer.valueOf(ch[i]))));
            System.out.println(Integer.toBinaryString((int)ch[i]).length());
        }
        Method[] methods = String.class.getMethods();
        for(int i=0;i<methods.length;i++){
//            System.out.println(methods[i].getName());
            Parameter[] parameters = methods[i].getParameters();
            for (int j=0;j<parameters.length;j++){
//                System.out.println(parameters[j].getName());
//                System.out.println(parameters[j].getType());
            }
        }
        Class[] classes = HashMap.class.getDeclaredClasses();
        for (int i=0;i<classes.length;i++){
            System.out.println(classes[i].getName());
        }
        System.out.println(String.class.getMethod("indexOf", int.class).getName());
        System.out.println(String.class.getMethod("indexOf", int.class).invoke("ssdd", 'd'));

//        String.class.getClassLoader().loadClass("java.lang.String");
        HashMap hashMap = new HashMap();
        System.out.println(hashMap.getClass().getClassLoader());
        BookFacade bookFacade = new BookFacadeImpl();
        System.out.println(bookFacade.getClass().getClassLoader().loadClass("java.lang.String"));
        System.out.println(bookFacade.getClass().getClassLoader().loadClass("com.test.proxy.TestProxy"));

        位移 wei = new 位移();
        int t = wei.tableSizeFor(15);
        System.out.println("t:"+t);
    }

    int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 20) ? 20 : n + 1;
    }

}
