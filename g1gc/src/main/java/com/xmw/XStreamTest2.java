package com.xmw;

import com.thoughtworks.xstream.XStream;

/**
 * @author xmw.
 * @date 2018/6/30 21:52.
 */
public class XStreamTest2 {
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            XStream xs = new XStream();
            xs.toString();
            xs = null;
            if (i++ % 10000 == 0) {
                System.gc();
            }
        }
    }
}
