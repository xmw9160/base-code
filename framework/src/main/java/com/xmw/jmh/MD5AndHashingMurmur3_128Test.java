package com.xmw.jmh;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.Md5Crypt;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * md5和HashingMurmur3_128 性能测试
 *
 * @author mingwei.xia
 * @date 2018/11/5 10:00
 * @since V1.0
 */
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
public class MD5AndHashingMurmur3_128Test {

    private static final Logger logger = LoggerFactory.getLogger(MD5AndHashingMurmur3_128Test.class);

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(MD5AndHashingMurmur3_128Test.class.getName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void testMd5() {
        for (int i = 0; i < 100; i++) {
            logger.debug(Md5Crypt.md5Crypt(String.valueOf(i).getBytes()));
        }
    }

    @Benchmark
    public void testMurmur3_128() {
        HashFunction murmur3_128 = Hashing.murmur3_128();
        for (int i = 0; i < 100; i++) {
            logger.debug(murmur3_128.hashBytes(String.valueOf(i).getBytes()).toString());
        }
    }

    @Benchmark
    public void testGoodFastHash() {
        HashFunction gfh = Hashing.goodFastHash(128);
        for (int i = 0; i < 100; i++) {
            logger.debug(gfh.hashBytes(String.valueOf(i).getBytes()).toString());
        }
    }
}
