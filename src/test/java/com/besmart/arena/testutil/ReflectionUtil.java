package com.besmart.arena.testutil;

import lombok.experimental.UtilityClass;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.springframework.util.ReflectionUtils.findField;

//TODO: refactor
@UtilityClass
public class ReflectionUtil {

    public static <V> V getFieldValue(Object target, String fieldName, Class<V> valueType) {
        Field field = getField(target, fieldName);
        field.setAccessible(true);
        try {
            Object value = getFieldValue(field, target);
            return valueType.cast(value);
        } finally {
            field.setAccessible(false);
        }
    }

    private static Field getField(Object target, String fieldName) {
        return Objects.requireNonNull(findField(target.getClass(), fieldName));
    }

    private static Object getFieldValue(Field field, Object target) {
        return ReflectionUtils.getField(field, target);
    }
}
