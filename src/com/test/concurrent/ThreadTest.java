package com.test.concurrent;

/**
 * @author zhouj
 * @since 2020-07-07
 */
public class ThreadTest {

    private int i=0;
    private Thread thread1,thread2,thread3;
    private int flag=0;
    public void runThread() throws InterruptedException{
        thread1=new Thread(new Thread1());
        thread2=new Thread(new Thread2());
        thread3=new Thread(new Thread3());
        thread1.start();
        thread2.start();
        thread3.start();
    }
    public class Thread1 implements Runnable{

        public void run() {

            while(i<=100){
                if(flag==0) {
                    System.out.println("t1="+i);
                    i++;
                    flag=1;
                }
            }
        }

    }

    public class Thread2 implements Runnable{

        public void run() {

            while(i<=100){
                if(flag==1) {
                    System.out.println("t2="+i);
                    i++;
                    flag=2;
                }
            }
        }

    }

    public class Thread3 implements Runnable{

        public void run() {

            while(i<=100){
                if(flag==2) {
                    System.out.println("t3="+i);
                    i++;
                    flag=0;
                }
            }
        }

    }

    public static void main(String[] args) {
        ThreadTest threadTest =  new ThreadTest();
        try {
            threadTest.runThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
