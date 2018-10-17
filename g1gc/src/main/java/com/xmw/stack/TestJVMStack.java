package com.xmw.stack;

/**
 * @author xmw.
 * @date 2018/6/27 23:56.
 */
public class TestJVMStack {
    private int count = 0;

    public static void main(String[] args) {
        TestJVMStack jvmStack = new TestJVMStack();
        jvmStack.testStack();
    }

    // 没有出口的递归函数
    public void recursion() {
        count++;
        recursion();
    }

    public void testStack() {
        try {
            recursion();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("deep of stack is " + count);
        }
    }
}
