package com.test.jvm;

/**
 * @author zhouj
 * @since 2019/7/25
 *
 * 使用-Xss参数减少栈内存容量，会抛出StackOverflowError异常，异常出现时输出的堆栈深度相应的缩小。
 * 增大方法中的本地变量表的长度，结果抛出StackOverflowError异常，输出的堆栈深度相应的缩小。
 * TIPS:以上实验结果表明，在单线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候都会抛出StackOverflowError异常。

 */
public class TestStackOver {

    private int stackLength = 1;

    public void stackLength() {
        int a =1;
        int b=2;
        stackLength++;
        stackLength();
    }

    public void statck() {
        while(true) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    while(true) {

                    }

                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {

        TestStackOver vm = new TestStackOver();
        try {
            vm.stackLength();
        }catch (Throwable e) {
            System.out.println("栈深度是："+vm.stackLength);
            throw e;
        }

    }
}
