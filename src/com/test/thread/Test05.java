package com.test.thread;

/**
 * Created by zhouj on 16/3/24.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AddThread implements Runnable {
    private List<Double> list;

    public AddThread(List<Double> list) {
        this.list = list;
    }

    public void run() {
        for(int i = 0; i < 10000; ++i) {
            list.add(Math.random());
        }
    }
}

public class Test05 {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        List<Double> list = new ArrayList();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();
    }
}

