package com.xmw.guava;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Guava Hashing
 * https://my.oschina.net/realfighter/blog/411813
 *
 * @author mingwei.xia
 * @date 2018/11/5 9:18
 * @since V1.0
 */
public class HashingDemo {

    public static void main(String[] args) {
        //XXX 校验总和Hash函数
        // 校验总和算法：Adler-32和CRC-32
        HashFunction adler32 = Hashing.adler32();
        // 5d01bc02
        System.out.println(adler32.hashBytes("xmw".getBytes()).toString());
        // 8f1191bb
        HashFunction crc32 = Hashing.crc32();
        System.out.println(crc32.hashBytes("xmw".getBytes()).toString());

        //XXX 一般的Hash函数，一般的散列函数非加密,适合用于基于散列的查询任务.
        // goodFastHash方法返回一个最小包含128长度bit位，一个字节有8个bit，
        // 因此调用goodFastHash 至少返回16个字节（128 / 8）
        HashFunction gfh = Hashing.goodFastHash(128);
        // c0c54b581b6637e05ebe9b94f89b87ff
        System.out.println(gfh.hashBytes("xmw".getBytes()).toString());
        HashFunction murmur3_32 = Hashing.murmur3_32();
        // d59cd1db
        System.out.println(murmur3_32.hashBytes("xmw".getBytes()).toString());
        // murmur3_128算法生成hash效率比md5效率高
        HashFunction murmur3_128 = Hashing.murmur3_128();
        // 1cab640f762172d6e7e37d0a690d745b
        System.out.println(murmur3_128.hashBytes("xmw".getBytes()).toString());

        //XXX 加密Hash函数
        // 数据的任何小变化，产生的Hash码都会发生大的变化
        // 通过反向工程，根据Hash code值推算出原始的数据是不可能的
        HashFunction sha1 = Hashing.sha1();
        //40位: 4a3d0d0b2d19716be45c09c43802926f515eb54c
        System.out.println(sha1.hashBytes("xmw".getBytes()).toString());
        HashFunction sha256 = Hashing.sha256();  // 一般使用sha256足够
        //64位: 4f2e194c36b5ba1b2c7d8a1daa1aa0c1fb3d187652125f47f973081165b75332
        System.out.println(sha256.hashBytes("xmw".getBytes()).toString());
        HashFunction sha512 = Hashing.sha512();
        //128位: ed6485da33f56528642c22515ce0c01986ab886c4c7e1dbc23018ecc7f49221b3593d627a1068c9f588f69b59848913682e18d58f8bcc234bcb40af2eff6cedb
        System.out.println(sha512.hashBytes("xmw".getBytes()).toString());
    }
}
