package com.test.reflect;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Field;

/**
 * @author zhouj
 * @since 2017/4/10
 */
public class MyClassLoader extends ClassLoader{

    private String path="d:\\";

    private final String fileType = ".class";

    // 类加载器名字

    private String name = null;

    public MyClassLoader(String name){

        super();

        this.name = name;

    }

    public MyClassLoader(ClassLoader parent,String name){

        super(parent);

        this.name = name;

    }

    // 调用getClassLoader()时返回此方法，如果不重载，则显示MyClassLoader的引用地址

    public String toString(){

        return this.name;

    }

    // 设置文件加载路径

    public void setPath(String path){

        this.path = path;

    }

    protected Class findClass(String name) throws ClassNotFoundException{

        byte[] data = loadClassData(name);

        // 参数off代表什么？

        return defineClass(name,data,0,data.length);

    }

    // 将.class文件读入内存中，并且以字节数形式返回

    private byte[] loadClassData(String name) throws ClassNotFoundException{

        FileInputStream fis = null;

        ByteArrayOutputStream baos = null;

        byte[] data = null;

        try{

            // 读取文件内容

            name = name.replaceAll("\\.","\\\\");

            System.out.println("加载文件名："+name);

            // 将文件读取到数据流中

            fis = new FileInputStream(path+name+fileType);

            baos = new ByteArrayOutputStream();

            int ch = 0;

            while ((ch = fis.read()) != -1){

                baos.write(ch);

            }

            data = baos.toByteArray();

        }catch (Exception e){

            throw new ClassNotFoundException("Class is not found:"+name,e);

        }finally {

            // 关闭数据流

            try {

                fis.close();

                baos.close();

            }catch (Exception e){

                e.printStackTrace();

            }

        }

        return data;

    }

    public static void main(String[] args) throws Exception {

        MyClassLoader loader1 = new MyClassLoader("loader1");

        // 获取MyClassLoader加载器

        System.out.println("MyClassLoader 加载器：" + MyClassLoader.class.getClassLoader());

        // 设置加载类查找文件路径

        loader1.setPath("D:\\workspace\\bac5\\java\\");

        loader(loader1);

    }

    private static void loader(MyClassLoader loader) throws Exception {

        // MyClassLoader 由系统加载器加载，跟test是不同的加载器，会出现NOClassDefFoundError

        // 如果类中有package，则加载类名时，需要写全，不然找不到该类,会出现NOClassDefFoundError

        Class test = loader.loadClass("test");

        Object test1 = test.newInstance();

        // test test2 = (test) test1;

        // 如果MyClassLoader与test非同一个加载器，访问时，需要用到反射机制

        Field v1 = test.getField("v1");// java反射机制,取test中的静态变量

        System.out.println("被加载出来的类是："+v1.getInt(test1));

// 卸载，将引用置空

        test = null;

        test1 = null;

        // 重新加载

        test = loader.loadClass("test");

        test1 = test.newInstance();

        System.out.println("test1 hashcode:"+test1.hashCode());

    }
}
