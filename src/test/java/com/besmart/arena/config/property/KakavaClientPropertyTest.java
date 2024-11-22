package com.besmart.arena.config.property;

import com.besmart.arena.base.AbstractSpringBootTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.besmart.arena.testutil.ConstraintViolationUtil.validateExpectingOneViolation;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class KakavaClientPropertyTest extends AbstractSpringBootTest {

    @Autowired
    private KakavaClientProperty property;

    @Autowired
    private Validator validator;

    @Test
    public void propertyShouldBeCreated() {
        var expected = new KakavaClientProperty(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "EN");
        assertEquals(expected, property);
    }

    @Test
    public void propertyShouldBeValid() {
        var givenProperty = new KakavaClientProperty(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "EN");

        Set<ConstraintViolation<KakavaClientProperty>> violations = validator.validate(givenProperty);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfVenueIdIsNull() {
        KakavaClientProperty givenProperty = KakavaClientProperty.builder()
                .language("EN")
                .build();

        validateExpectingOneViolation(givenProperty, validator, "must not be null");
    }

    @Test
    public void propertyShouldNotBeValidBecauseOfLanguageIsBlank() {
        var givenProperty = new KakavaClientProperty(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "  \n");

        validateExpectingOneViolation(givenProperty, validator, "must not be blank");
    }
}
