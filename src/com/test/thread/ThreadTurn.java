package com.test.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther zhouj
 * @since 2018/9/21
 */
public class ThreadTurn {
    Object o = new Object();
    Boolean f = true ; // True 时线程1执行

    class Thread1 extends Thread{
        public void run() {
            synchronized (o) {
                for (int i = 0; i < 100; i++) {
                    System.out.print("A线程--->"+i);
                    o.notify();
                    if(f){
                        Set<Character> set = new HashSet<>();
                        f = false;
                        try{
                            o.wait();
                        }catch(Exception e){
                            System.out.print(e);
                        }
                    }
                }
            }
        }
    }

    class Thread2 extends Thread{
        public void run(){
            synchronized (o){
                for(int i=0;i<100;i++){
                    System.out.println(" B线程--->"+i);
                    o.notify();
                    if(!f){
                        f = true;
                        try {
                            o.wait();
                        }catch(Exception e){
                            System.out.print(e);
                        }
                    }
                }
            }
        }
    }

    public void start(){
        new Thread1().start();
        new Thread2().start();
    }

    public static void main(String args[]){
        ThreadTurn t = new ThreadTurn();
        t.start();
    }

}
