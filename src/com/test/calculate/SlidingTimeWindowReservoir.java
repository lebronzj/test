//package com.test.calculate;
//
///**
// * @author zhouj
// * @since 2021-06-24
// */
//
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * 熔断机制的一种实-- 滑动窗口
// * 清除超过一个窗口的，统计窗口内的
// * 参考：https://github.com/infusionsoft/yammer-metrics/blob/master/metrics-core/src/main/java/com/codahale/metrics/SlidingTimeWindowReservoir.java
// * A {@link Reservoir} implementation backed by a sliding window that stores only the measurements made
// * in the last {@code N} seconds (or other time unit).
// */
//public class SlidingTimeWindowReservoir {
//    //防止冲突的buffer
//    // allow for this many duplicate ticks before overwriting measurements
//    private static final int COLLISION_BUFFER = 256;
//    //每隔多长时间进行一次蓄水池Reservoir的清除
//    // only trim on updating once every N
//    private static final int TRIM_THRESHOLD = 256;
//
//    private final Clock clock;
//    private final ConcurrentSkipListMap<Long, Long> measurements;
//    private final long window;
//    private final AtomicLong lastTick;
//    private final AtomicLong count;
//
//    /**
//     * Creates a new {@link SlidingTimeWindowReservoir} with the given window of time.
//     *
//     * @param window     the window of time
//     * @param windowUnit the unit of {@code window}
//     */
//    public SlidingTimeWindowReservoir(long window, TimeUnit windowUnit) {
//        this(window, windowUnit, Clock.defaultClock());
//    }
//
//    /**
//     * Creates a new {@link SlidingTimeWindowReservoir} with the given clock and window of time.
//     *
//     * @param window     the window of time
//     * @param windowUnit the unit of {@code window}
//     * @param clock      the {@link Clock} to use
//     */
//    public SlidingTimeWindowReservoir(long window, TimeUnit windowUnit, Clock clock) {
//        this.clock = clock;
//        this.measurements = new ConcurrentSkipListMap<Long, Long>();
//        this.window = windowUnit.toNanos(window) * COLLISION_BUFFER;
//        this.lastTick = new AtomicLong();
//        this.count = new AtomicLong();
//    }
//
//    @Override
//    public int size() {
//        trim();
//        return measurements.size();
//    }
//
//    @Override
//    public void update(long value) {
//        if (count.incrementAndGet() % TRIM_THRESHOLD == 0) {
//            trim();
//        }
//        measurements.put(getTick(), value);
//    }
//
//    @Override
//    public Snapshot getSnapshot() {
//        trim();
//        return new Snapshot(measurements.values());
//    }
//
//    private long getTick() {
//        for (; ; ) {
//            final long oldTick = lastTick.get();
//            final long tick = clock.getTick() * COLLISION_BUFFER;
//            // ensure the tick is strictly incrementing even if there are duplicate ticks
//            System.out.println("test" +count);
//            final long newTick = tick > oldTick ? tick : oldTick + 1;
//            if (lastTick.compareAndSet(oldTick, newTick)) {
//                return newTick;
//            }
//        }
//    }
//
//    private void trim() {
//        measurements.headMap(getTick() - window).clear();
//    }
//}
