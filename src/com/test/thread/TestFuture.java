package com.test.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhouj
 * @since 2020-07-30
 */
@Slf4j
public class TestFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("callable execute ...");
                Thread.sleep(5000);
                return "done";
            }
        });
        log.info("execute something in main ...");
        Thread.sleep(1000);
        log.info("future is done ? {}", future.isDone());
        log.info("result: {}", future.get());
        log.info("future is done ? {}", future.isDone());
    }
}
