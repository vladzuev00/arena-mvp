package com.besmart.arena.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

public final class ShowCustomMarkTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void markShouldBeSerializedToJson()
            throws Exception {
        ShowCustomMarkTO givenMark = new ShowCustomMarkTO(
                "Akcija",
                "FF006F",
                "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
        );

        String actual = objectMapper.writeValueAsString(givenMark);
        String expected = """
                {
                  "name": "Akcija",
                  "color": "FF006F",
                  "pictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void markShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                  "name": "Akcija",
                  "color": "FF006F",
                  "pictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
                }""";

        ShowCustomMarkTO actual = objectMapper.readValue(givenJson, ShowCustomMarkTO.class);
        ShowCustomMarkTO expected = new ShowCustomMarkTO(
                "Akcija",
                "FF006F",
                "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
        );
        Assertions.assertEquals(expected, actual);
    }
}
