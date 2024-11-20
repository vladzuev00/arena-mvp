package com.besmart.arena.client.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

public final class ShowMarkTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void markShouldBeSerializedToJsonValue()
            throws Exception {
        ShowMarkTO givenMark = new ShowMarkTO("test-name");

        String actual = objectMapper.writeValueAsString(givenMark);
        String expected = """
                {
                  "name": "test-name"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void markShouldBeDeserializedFromJsonValue()
            throws Exception {
        String givenJson = """
                {
                  "name": "Akcija",
                  "color": "FF006F",
                  "pictureUrl": ""
                }""";

        ShowMarkTO actual = objectMapper.readValue(givenJson, ShowMarkTO.class);
        ShowMarkTO expected = new ShowMarkTO("Akcija");
        Assertions.assertEquals(expected, actual);
    }
}
