package com.test.volatiles;

/**
 * @author zhouj
 * @since 2020-05-12
 */
public class AAndB {

    int x = 0;
    int y = 0;
    int a = 0;
    int b = 0;

    public void awrite() {

        a = 1;
        x = b;
    }

    public void bwrite() {

        b = 1;
        y = a;
    }

    public static void main(String[] args) {
        try {
            testReSort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testReSort() throws InterruptedException {

        AAndB aAndB = new AAndB();

        for (int i = 0; i < 1000000; i++) {
            AThread aThread = new AThread(aAndB);
            BThread bThread = new BThread(aAndB);

            aThread.start();
            bThread.start();
            aThread.join();
            bThread.join();

            if (aAndB.y == 0 && aAndB.x == 0) {
                System.out.println("resort," + "a:" + aAndB.a + ",b:" + aAndB.b + ",x:" + aAndB.x + ",y:" + aAndB.y);
            }
            aAndB.x = aAndB.y = aAndB.a = aAndB.b = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                    }
                }
            }).start();
        }

        System.out.println("end");
    }
}

class AThread extends Thread {

    private AAndB aAndB;

    public AThread(AAndB aAndB) {
        this.aAndB = aAndB;
    }

    @Override
    public void run() {
        synchronized (aAndB) {
            this.aAndB.awrite();
        }
//        super.run();
    }
}

class BThread extends Thread {

    private AAndB aAndB;

    public BThread(AAndB aAndB) {
        this.aAndB = aAndB;
    }

    @Override
    public void run() {
        synchronized (aAndB) {
            this.aAndB.bwrite();

        }
//        super.run();
    }
}


