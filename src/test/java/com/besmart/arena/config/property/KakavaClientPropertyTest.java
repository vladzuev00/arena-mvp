package com.besmart.arena.config.property;

import com.besmart.arena.base.AbstractSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

///TODO: add tests for validation
public final class KakavaClientPropertyTest extends AbstractSpringBootTest {

    @Autowired
    private KakavaClientProperty property;

    @Test
    public void propertyShouldBeCreated() {
        KakavaClientProperty expected = new KakavaClientProperty(
                fromString("123e4567-e89b-42d3-a456-556642440000"),
                "password",
                "testuser@kakava.lt",
                "testpassword",
                "https://app-kkv-be-test.azurewebsites.net",
                "/Token",
                "/event/show",
                "/order",
                "/arena/admission"
        );
        assertEquals(expected, property);
    }
}
