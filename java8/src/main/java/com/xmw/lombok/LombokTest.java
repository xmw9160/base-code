package com.xmw.lombok;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.val;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Date 2018/1/29.
 * Author xmw .
 */
public class LombokTest {

    public static void main(String[] args) {
        LombokEntity entity = new LombokEntity();
        entity.setAddress("四川渠县");
        entity.setAge(10);
        System.out.println(entity.getAddress());
        System.out.println(entity.getAge());

        val sets = new HashSet<String>();
        val lists = new ArrayList<String>();
        val maps = new HashMap<String, String>();
        //=>相当于如下
        final Set<String> sets2 = new HashSet<>();
        final List<String> lists2 = new ArrayList<>();
        final Map<String, String> maps2 = new HashMap<>();

        try {
            @Cleanup InputStream inputStream = new FileInputStream(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=>相当于
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void notNullExample(@NonNull String string) {
        string.length();
    }

    //=>相当于
    public void notNullExample1(String string) {
        if (string != null) {
            string.length();
        } else {
            throw new NullPointerException("null");
        }
    }
}
