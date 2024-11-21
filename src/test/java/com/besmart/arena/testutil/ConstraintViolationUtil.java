package com.besmart.arena.testutil;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.experimental.UtilityClass;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public final class ConstraintViolationUtil {

    public static <T> void validateExpectingOneViolation(T object, Validator validator, String expected) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        assertEquals(1, violations.size());
        String actual = violations.iterator().next().getMessage();
        assertEquals(expected, actual);
    }
}
