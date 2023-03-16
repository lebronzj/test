package com.test.thread;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zhouj
 * @since 2023-02-08
 */
public class UnlockTest {

    /**
     * 其一，从getUnsafe方法的使用限制条件出发，通过Java命令行命令-Xbootclasspath/a把调用Unsafe相关方法的类A所在jar包路径追加到默认的bootstrap路径中，使得A被引导类加载器加载，从而通过Unsafe.getUnsafe方法安全的获取Unsafe实例。
     * java -Xbootclasspath/a: ${path} // 其中path为调用Unsafe相关方法的类所在jar包路径
     * 其二，通过反射获取单例对象theUnsafe.
     *
     */
    static Unsafe unsafe;

    static {
        initUnsafe();
    }

    /**
     * 反射获取unsafe
     */
    private static void initUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe1 =  (Unsafe) f.get(null);
            unsafe = unsafe1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    private volatile int a = 0;

    private int b = 0;

    public static void main(String[] args) {
        UnlockTest unlockTest = new UnlockTest();
        for(int i =0;i<100;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    unlockTest.test();
                }
            },"test"+i);
            thread.start();
        }
    }



    @SneakyThrows
    public void test(){
        long offset = unsafe.objectFieldOffset
                (UnlockTest.class.getDeclaredField("a"));
        for(;;){
            if(unsafe.compareAndSwapInt(this,offset,0,1)){
                for(int i=0;i<100;i++){
                    b++;
                }
                unsafe.compareAndSwapInt(this,offset,1,0);
                System.out.println(b);
                break;
            }
        }

    }
}
