package com.besmart.arena.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

public final class CategoryTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void categoryShouldBeSerializedToJson()
            throws Exception {
        CategoryTO givenCategory = new CategoryTO(255);

        String actual = objectMapper.writeValueAsString(givenCategory);
        String expected = """
                {
                  "id": 255
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void categoryShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                  "id": 4
                }""";

        CategoryTO actual = objectMapper.readValue(givenJson, CategoryTO.class);
        CategoryTO expected = new CategoryTO(4);
        Assertions.assertEquals(expected, actual);
    }
}
