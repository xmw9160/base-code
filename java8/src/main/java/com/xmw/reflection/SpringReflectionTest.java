package com.xmw.reflection;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.ReflectionUtils;

import io.netty.util.internal.PlatformDependent;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/9/6 11:33
 * @since V1.0
 */
public class SpringReflectionTest {


    public static void main(String[] args) {
        AtomicLong directMemory;
        Field field = ReflectionUtils.findField(PlatformDependent.class, "DIRECT_MEMORY_COUNTER");
        field.setAccessible(true);

        try {
            directMemory = (AtomicLong) field.get(PlatformDependent.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(new Date());
        ScheduledFuture<?> scheduledFuture = Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> System.out.println(new Date()), 5, 1, TimeUnit.SECONDS);
    }
}
