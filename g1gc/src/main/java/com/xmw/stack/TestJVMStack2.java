package com.xmw.stack;

/**
 * @author xmw.
 * @date 2018/6/27 23:56.
 */
public class TestJVMStack2 {
    private int count = 0;

    public static void main(String[] args) {
        TestJVMStack2 jvmStack = new TestJVMStack2();
        jvmStack.testStack();
    }

    // 没有出口的递归函数
    public void recursion(long a, long b, long c) {
        long d = 0, e = 0, f = 0;
        count++;
        recursion(a, b, c);
    }

    public void testStack() {
        try {
            recursion(1L, 2L, 3L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("deep of stack is " + count);
        }
    }
}
