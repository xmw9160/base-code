package com.xmw.guava;

/**
 * Guava BloomFilter
 * https://my.oschina.net/realfighter/blog/411813
 * Bloom Filter是一个独特的数据结构，用来确定一个元素是否存在于一个集合中。
 * 有意思的一点是，它能准确的判断一个元素不存在，不能准确的判断元素存在
 * <p>
 * BloomFilter本质上是位向量。它以如下方式工作：
 * 1. 添加一个元素到filter中
 * 2. 将这个元素进行多次Hash运算，将得到的hash值的bit位设置为1
 *
 * @author mingwei.xia
 * @date 2018/11/5 9:45
 * @since V1.0
 */
public class TestBloomFilter {
}
