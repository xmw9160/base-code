package com.xmw.apache.commons;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * Codec提供了一些公共的编解码实现，比如Base64, Hex, MD5,Phonetic and URLs等等。
 *
 * @author xmw.
 * @date 2018/11/4 10:46 PM.
 */
public class TestCodec {
    public static void main(String[] args) {
        // eG13Li4=
        System.out.println(encodeTest("xmw.."));
        // xmw..
        decodeTest("eG13Li4=");

        // md5
        System.out.println(Md5Crypt.md5Crypt("xmw".getBytes()));
    }

    //Base64编解码
    private static String encodeTest(String str) {
        Base64 base64 = new Base64();
        try {
            str = base64.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("Base64 编码后：" + str);
        return str;
    }

    private static void decodeTest(String str) {
        Base64 base64 = new Base64();
        //str = Arrays.toString(Base64.decodeBase64(str));
        str = new String(Base64.decodeBase64(str));
        System.out.println("Base64 解码后：" + str);
    }
}
