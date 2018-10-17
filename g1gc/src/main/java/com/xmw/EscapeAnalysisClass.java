package com.xmw;

/**
 * @author xmw.
 * @date 2018/6/28 23:23.
 */
public class EscapeAnalysisClass {

    public static B b;

    // 给全部变量赋值, 发生逃逸
    public void globalVariablePointerEscape() {
        b = new B();
    }

    // 方法返回值, 发生逃逸
    public B methodPointerEscape() {
        return new B();
    }

    public void instancePassPointerEscape() {
        //实例引用发生逃逸
        methodPointerEscape().printClassName(new G());
    }
}


class B {
    public void printClassName(G g) {
        System.out.println(g.getClass().getName());
    }
}

class G {
    public B b;

    // 给全部变量赋值, 发生逃逸
    public void globalVariablePointerEscape() {
        b = new B();
    }

    // 方法返回值, 发生逃逸
    public B methodPointerEscape() {
        return new B();
    }

    public void instancePassPointerEscape() {
        //实例引用发生逃逸
        methodPointerEscape().printClassName(this);
    }
}

