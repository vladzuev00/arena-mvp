package com.besmart.arena.client.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.UUID.fromString;

public final class PromoterTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void promoterShouldBeSerializedToJson()
            throws Exception {
        PromoterTO givenPromoter = new PromoterTO(fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"), "Organizatorius Z, VŠĮ");

        String actual = objectMapper.writeValueAsString(givenPromoter);
        String expected = """
                {
                   "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",
                   "name": "Organizatorius Z, VŠĮ"
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void promoterShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                  "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",
                  "name": "Organizatorius Z, VŠĮ",
                  "address": "Didlaukio g. 45, 08321, Vilnius, Lietuva",
                  "companyCode": "123123789",
                  "vatCode": "123456",
                  "email": "testkakava@gmail.com",
                  "phone": "+37060000001",
                  "pictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2",
                  "brandName": "ORGZ"
                }""";

        PromoterTO actual = objectMapper.readValue(givenJson, PromoterTO.class);
        PromoterTO expected = new PromoterTO(fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"), "Organizatorius Z, VŠĮ");
        Assertions.assertEquals(expected, actual);
    }
}
