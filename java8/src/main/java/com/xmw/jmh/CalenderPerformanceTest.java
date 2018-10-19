package com.xmw.jmh;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Calender 性能基准测试
 *
 * @author mingwei.xia
 * @date 2018/6/25 9:35
 * @since V1.0
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class CalenderPerformanceTest {

    private static int millis = 24 * 60 * 60 * 1000;

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(CalenderPerformanceTest.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(5)
    public void runCalendar() {
        Calendar calendar = Calendar.getInstance();
    }

    @Benchmark
    @Threads(5)
    public void runJodaTime() {
        DateTime dateTime = new DateTime();
    }

    @Benchmark
    @Threads(5)
    public void runSystem() {
        long result = System.currentTimeMillis() / millis;
    }
}
