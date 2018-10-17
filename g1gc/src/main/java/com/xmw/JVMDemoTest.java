package com.xmw;

/**
 * @author xmw.
 * @date 2018/6/24 23:07.
 */
public class JVMDemoTest {
    public static void main(String[] args) {
        System.out.println("memory info " + toMemoryInfo());
    }

    public static String toMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        int freeMemory = (int) (runtime.freeMemory() / 1024 / 1024);
        int totalMemory = (int) (runtime.totalMemory() / 1024 / 1024);
        return freeMemory + "M/" + totalMemory + "M/(free/total)";
    }
}
