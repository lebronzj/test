package com.test.classLoader;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.BatchUpdateException;

/**
 * @author zhouj
 * @since 2020-04-24
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String filePath = "/Users/zhouj/Downloads/" + name.replace('.', File.separatorChar) + ".class";
            //指定读取磁盘上的某个文件夹下的.class文件：
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            //调用defineClass方法，将字节数组转换成Class对象
            Class<?> clazz = this.defineClass(name, bytes, 0, bytes.length);
            fis.close();
            return clazz;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("classLoader:" + ClassLoader.getSystemClassLoader().getClass());
        for (String path : System.getProperty("sun.boot.class.path").split(":")) {
            System.out.println("boot:" + path);
        }
        for (String path : System.getProperty("java.ext.dirs").split(":")) {
            System.out.println("ext:" + path);
        }
        for (String path : System.getProperty("java.class.path").split(":")) {
            System.out.println("class:" + path);
        }
        System.out.println(BatchUpdateException.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        ClassLoader classLoader = new MyClassLoader();
        System.out.println("beforeContextClassLoad:" + Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(classLoader);
        System.out.println("afterContextClassLoad:" + Thread.currentThread().getContextClassLoader());
        Class clazz0 = classLoader.loadClass("com.acong.ssoserver.ServerApp");
        Class clazzSon = classLoader.loadClass("com.test.classLoader.MySonClassLoader");
        System.out.println("clazzSonClassLoader:" + clazzSon.getClassLoader());
        Method method = clazzSon.getMethod("getParent");
        System.out.println("systemClassLoader:" + ClassLoader.getSystemClassLoader());
        System.out.println("systemClassLoader:" + ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println("clazzSon:" + clazzSon.getClassLoader());
        System.out.println("sonParent:" + method.invoke(clazzSon.newInstance()));
        System.out.println(clazz0.getName());
        System.out.println(clazz0.getClassLoader());
        System.out.println(classLoader.getParent());

        File file = new File("/Users/zhouj/Downloads/arthas-demo.jar");
        MyUrlClassLoader myUrlClassLoader = new MyUrlClassLoader(new URL[]{file.toURI().toURL()}, MyClassLoader.getSystemClassLoader());
        Class c = myUrlClassLoader.loadClass("demo.MathGame");
        Method method1 = c.getMethod("main", String[].class);
        method1.invoke(null, (Object) null);
        Class d = myUrlClassLoader.loadClass("com.test.proxy.BookFacadeProxy");
        System.out.println("MathGame:" + c.getClassLoader());
        System.out.println("BookFacadeProxy:" + d.getClassLoader());
        System.out.println(Unsafe.class.getClassLoader());
        System.out.println(myUrlClassLoader.getParent());
    }
}
