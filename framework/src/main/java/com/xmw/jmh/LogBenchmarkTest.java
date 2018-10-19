package com.xmw.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印基准测试
 *
 * @author mingwei.xia
 * @date 2018/6/26 12:52
 * @since V1.0
 */
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
public class LogBenchmarkTest {

    private static final Logger logger = LoggerFactory.getLogger(LogBenchmarkTest.class);

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(LogBenchmarkTest.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    /**
     * 创建x,y,z三个字符串变量，然后在循环中，使用字符串连接的形式将调试日志输出
     */
    @Benchmark
    public void testConcatenatingStrings() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;
            logger.debug("Concatenating strings " + x + y + z);
        }
    }

    /**
     * 使用变量参数来代替字符串连接，更改代码内容如下，然后打包执行
     */
    @Benchmark
    public void testVariableArguments() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;
            logger.debug("Variable arguments {} {} {}", x, y, z);
        }
    }

    /**
     * 使用日志输出时使用isDebugEnabled()进行优化
     */
    @Benchmark
    public void testIfDebugEnabled() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 100; i++) {
            x += i;
            y += i;
            z += i;
            if (logger.isDebugEnabled())
                logger.debug("If debug enabled {} {} {}", x, y, z);
        }
    }
}
