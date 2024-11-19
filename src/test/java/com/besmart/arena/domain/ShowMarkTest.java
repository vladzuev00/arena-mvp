package com.besmart.arena.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public final class ShowMarkTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void markShouldBeSerializedToJsonValue() {
        throw new RuntimeException();
    }
}
