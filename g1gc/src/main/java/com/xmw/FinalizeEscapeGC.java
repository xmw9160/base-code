package com.xmw;

/**
 * @author xmw.
 * @date 2018/6/29 0:22.
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        // 对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级低， 暂定0。5秒， 以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no ,i am dead");
        }

        // 下面这段代码与上面的代码完全相同， 但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级低， 暂定0。5秒， 以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no ,i am dead");
        }
    }

    public void isAlive() {
        System.out.println("yes , i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
}
