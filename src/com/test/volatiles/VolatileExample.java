package com.test.volatiles;

/**
 * @author zhouj
 * @since 2020/1/9
 */
public class VolatileExample extends Thread {
    //设置类静态变量,各线程访问这同一共享变量
    private static boolean  flag = false;
    private static int  x = 2;
    //利用x 是否为volatile可验证是否会刷新缓存重新获取 flag 和x的值
//    private static volatile Integer x = 0;

    //无限循环,等待flag变为true时才跳出循环 读的频率很高
    @Override
    public void run() {
        while (!flag) {
            x=2;
        }
    }

    public static class A {
        public static Integer x = 0;
    }

    public static void main(String[] args) throws Exception {
        new VolatileExample().start();
        //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
        Thread.sleep(100);
        flag = true;
    }

}
