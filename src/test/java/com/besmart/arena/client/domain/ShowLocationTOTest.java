package com.besmart.arena.client.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.UUID.fromString;

public final class ShowLocationTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void locationShouldBeSerializedToJson()
            throws Exception {
        ShowLocationTO givenLocation = new ShowLocationTO("Šiaulių arena");

        String actual = objectMapper.writeValueAsString(givenLocation);
        String expected = """
                {
                  "name": "Šiaulių arena"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void locationShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                  "id": "a60de864-5c52-11ee-a81c-000d3aa868a2",
                  "name": "Šiaulių arena",
                  "address": "ner"
                }""";

        ShowLocationTO actual = objectMapper.readValue(givenJson, ShowLocationTO.class);
        ShowLocationTO expected = new ShowLocationTO("Šiaulių arena");
        Assertions.assertEquals(expected, actual);
    }
}
