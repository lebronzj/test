package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhouj on 16/4/6.
 */
public class DynamicProxyTest {
     interface IHello {
         String sayHello();
    }

    static class DynaProxy implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("dynamic proxy");
            Method[] methods = proxy.getClass().getMethods();
            for(int i =0;i<methods.length;i++){
                System.out.println(methods[i].getName());
            }
            Class clas = proxy.getClass().getSuperclass();
            System.out.println(clas.getName());
            return new String("123");
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[]{IHello.class}, new DynaProxy());
        System.out.println(hello.getClass());
        String str = hello.sayHello();
        System.out.println(str);
    }
}