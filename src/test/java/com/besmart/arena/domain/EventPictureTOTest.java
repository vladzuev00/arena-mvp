package com.besmart.arena.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.client.domain.EventPictureTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

public final class EventPictureTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void pictureShouldBeSerializedToJson()
            throws Exception {
        EventPictureTO givenPicture = new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2");

        String actual = objectMapper.writeValueAsString(givenPicture);
        String expected = """
                {
                  "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void pictureShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                  "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2",
                  "mobilePictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2",
                  "coverPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
                }""";

        EventPictureTO actual = objectMapper.readValue(givenJson, EventPictureTO.class);
        EventPictureTO expected = new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2");
        Assertions.assertEquals(expected, actual);
    }
}
