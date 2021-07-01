package com.test.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.LongStream;

/**
 * @author zhouj
 * @since 2020-06-24
 */
public class ForkJoin {

    static Random random = new Random(0);

    static int random() {
        return random.nextInt(10000);
    }

    public static void main(String[] args) throws Exception {

        Instant start = Instant.now();
        LongStream.rangeClosed( 0,110 )
                //并行流
                .parallel()
                .reduce( 0,Long::sum );




        LongStream.rangeClosed( 0,110 )
                //顺序流
                .sequential()
                .reduce( 0,Long::sum );


        Instant end = Instant.now();

        System.out.println("耗费时间"+ Duration.between( start,end ).toMillis());

//        final ForkJoinPool mainPool = new ForkJoinPool();
//        int len = 1000 * 1000 * 10;
//        int[] array = new int[len];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = random();
//        }
//        mainPool.invoke(new SortTask(array, 0, len - 1));
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
    }

    public static class SortTask extends RecursiveAction {
        private int[] array;
        private int fromIndex;
        private int toIndex;
        private final int chunksize = 1024;

        public SortTask(int[] array, int fromIndex, int toIndex) {
            this.array = array;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected void compute() {
            int size = toIndex - fromIndex + 1;
            if (size < chunksize) {
                Arrays.sort(array, fromIndex, toIndex);
            } else {
                int leftSize = size / 2;
                SortTask leftTask = new SortTask(array, fromIndex, fromIndex + leftSize);
                SortTask rightTask = new SortTask(array, fromIndex + leftSize + 1, toIndex);
                invokeAll(leftTask, rightTask);
            }
        }
    }
}
