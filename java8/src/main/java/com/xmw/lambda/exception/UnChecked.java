package com.xmw.lambda.exception;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author xmw.
 * @date 2018/10/19 9:33 PM.
 */
public final class UnChecked {
    @FunctionalInterface
    interface ExceptionFunction<T, R> {
        R apply(T t) throws Exception;
    }

    public static <T, R> Function<T, R> warp(ExceptionFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception e) {
                // throwException(e);
                return null;
            }
        };
    }

    private static <T extends Exception> T throwException(Exception e) throws T{
        throw (T)e;
    }
}
