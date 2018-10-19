package com.xmw.lambda.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在java stream中抛出可检查异常
 * https://stackoverflow.com/questions/27644361/how-can-i-throw-checked-exceptions-from-inside-java-8-streams
 * @author xmw.
 * @date 2018/10/19 9:29 PM.
 */
public class Example {
    private static List<Class> findClasses(List<String> names) {
        return names.stream()
                .map(className -> LambdaExceptionUtil.uncheck(Class::forName, className))
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        try {
            findClasses(Arrays.asList("hello"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
