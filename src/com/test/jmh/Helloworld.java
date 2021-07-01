package com.test.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

/**
 * @author zhouj
 * @since 2020/1/8
 */
public class Helloworld {

    @Benchmark
    @BenchmarkMode({Mode.All})
    public void m() {
        for (int i = 0; i < 1_000_000; i++) {

        }

    }

}
