package com.study.payments;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    public static final Map<Class<?>, Object> LOCATOR = new HashMap<>();

    public static void register(Class<?> clazz, Object service) {
        LOCATOR.put(clazz, service);
    }

    public static <T> T get(Class<T> clazz) {
        return clazz.cast(LOCATOR.get(clazz));
    }

}
