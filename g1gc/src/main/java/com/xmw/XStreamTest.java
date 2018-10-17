package com.xmw;

import com.thoughtworks.xstream.XStream;

/**
 * @author xmw.
 * @date 2018/6/30 21:52.
 */
public class XStreamTest {
    public static void main(String[] args) {
        while (true) {
            XStream xs = new XStream();
            xs.toString();
            xs = null;
        }
    }
}
