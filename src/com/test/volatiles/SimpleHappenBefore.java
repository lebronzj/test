package com.test.volatiles;

/**
 * @author zhouj
 * @since 2020/1/9
 */

/**
 * 一个简单的展示Happen-Before的例子.
 * 这里有两个共享变量:a和flag,初始值分别为0和false.在ThreadA中先给a=1,然后flag=true.
 * 如果按照有序的话,那么在ThreadB中如果if(flag)成功的话,则应该a=1,而a=a*1之后a仍然为1,下方的if(a==0)应该永远不会为真,永远不会打印.
 * 但实际情况是:在试验100次的情况下会出现0次或几次的打印结果,而试验1000次结果更明显,有十几次打印.
 */
public class SimpleHappenBefore {
    static int x = 0;
    static int y = 0;
    static volatile int a = 0;
    static volatile int b = 0;

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<2000000;i++){
            reSort();
        }
    }

    static void reSort() throws InterruptedException {
        Thread t = new Thread(() -> {
            a = 1; //操作1
            x = b; //操作2
        });
        Thread j = new Thread(() -> {
            b = 1; //操作3
            y = a;  //操作4
        });
        t.start();
        j.start();
        t.join();
        j.join();
        if (x == 0 && y == 0) {
            System.out.println("(" + x + "," + y + ")");
        }
        x = 0;
        y = 0;
        a = 0;
        b = 0;
    }

}

