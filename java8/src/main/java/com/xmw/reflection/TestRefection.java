package com.xmw.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/5/3 21:02
 * @since V1.0
 */
public class TestRefection {
    public static void main(String[] args) throws Exception {
        Class clazz = MyClassObject.class;
        clazz = Class.forName("com.hcb.reflection.MyClassObject");
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Method getList = clazz.getMethod("getTest", String.class);
        System.out.println(Arrays.toString(getList.getParameterTypes()));
        System.out.println(getList.getReturnType());
        MyClassObject object1 = new MyClassObject();
        System.out.println(getList.invoke(object1, "xiamingwei"));
        for (Field field : fields) {
            System.out.println(field.getType());
            System.out.println(field.getName());
            MyClassObject object = new MyClassObject();
            Object value = field.get(object);
            System.out.println(value);
            field.set(object, value);
        }
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getPackage());
        int modifiers = clazz.getModifiers();
        System.out.println(modifiers);
        Modifier.isAbstract(modifiers);
//        Modifier.isFinal( modifiers)
//        Modifier.isInterface(int modifiers)
//        Modifier.isNative(int modifiers)
//        Modifier.isPrivate(int modifiers)
//        Modifier.isProtected(int modifiers)
//        Modifier.isPublic(int modifiers)
//        Modifier.isStatic(int modifiers)
//        Modifier.isStrict(int modifiers)
//        Modifier.isSynchronized(int modifiers)
//        Modifier.isTransient(int modifiers)
//        Modifier.isVolatile(int modifiers)
        Class superclass = clazz.getSuperclass();
        Class[] interfaces = clazz.getInterfaces();
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName());
            System.out.println(Arrays.toString(constructor.getParameterTypes()));
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }

        MyClassObject object2 = new MyClassObject(12);
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o = declaredField.get(object2);
            System.out.println(o);
        }
    }
}
