//package com.besmart.arena.client;
//
//import com.besmart.arena.client.domain.*;
//import com.besmart.arena.config.property.KakavaClientProperty;
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.ok;
//import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
//import static java.util.UUID.fromString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
////TODO: correct
//public final class KakavaArenaClientTest {
//    private WireMockServer server;
//    private WebClient webClient;
//    private KakavaArenaClient client;
//    private final KakavaClientProperty property = new KakavaClientProperty(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "EN");
//
//    @BeforeEach
//    public void startServer() {
//        server = new WireMockServer(wireMockConfig().dynamicPort());
//        server.start();
//        webClient = WebClient.builder().baseUrl(server.baseUrl()).build();
//        client = new KakavaArenaClient(webClient, property);
//    }
//
//    @AfterEach
//    public void shutdownServer() {
//        server.shutdown();
//    }
//
//    @Test
//    public void showsShouldBeRequested() {
//        String givenJson = """
//                {
//                    "eventBlocks": [],
//                    "shows": [
//                        {
//                            "id": "dbd5120c-5ec8-11ee-a81c-000d3aa868a2",
//                            "shortId": 2331,
//                            "eventId": "95825337-5ec8-11ee-a81c-000d3aa868a2",
//                            "eventShortId": 1755,
//                            "eventTitle": "Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket",
//                            "eventTitleForAnalytics": "Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket",
//                            "eventTitleSlug": "siauliu-arena-siauliu-siauliai-mazeikiu-m-basket",
//                            "eventTitleSlugs": [
//                                {
//                                    "language": "en",
//                                    "value": "siauliu-arena-siauliu-siauliai-mazeikiu-m-basket"
//                                },
//                                {
//                                    "language": "lt",
//                                    "value": "siauliu-arena-siauliu-siauliai-mazeikiu-m-basket"
//                                }
//                            ],
//                            "hasEnglishDescription": true,
//                            "eventDescription": "<a href=\\"https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\\" target=\\"_blank\\">taisyklės</a>&nbsp;Rajoną pavadinti&nbsp;<i><b>Pilaite</b></i>&nbsp;nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui&nbsp;<i><b>Sudervėlė</b></i>. Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.&nbsp;<i><b>Pilaitė</b></i>&nbsp;– istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis&nbsp;<i><b>Pilaitė</b></i>&nbsp;ir tikrai yra stovėjusi pilis. Todėl&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\\" title=\\"Lenkų kalba\\">lenk.</a>&nbsp;<i>Zameczek</i>&nbsp;yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.    <br>",
//                            "eventPicture": {
//                                "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2",
//                                "mobilePictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2",
//                                "coverPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"
//                            },
//                            "eventCategories": [
//                                {
//                                    "id": 4
//                                }
//                            ],
//                            "state": "Selling",
//                            "startDateTime": "2026-09-29T16:03:00.0000000",
//                            "endDateTime": "2026-09-29T18:03:00.0000000",
//                            "showDate": true,
//                            "distributionStartDateTime": "2023-09-29T16:08:00.0000000",
//                            "showPeriod": false,
//                            "duration": "1 men",
//                            "marks": [
//                                "PROMOTION"
//                            ],
//                            "customMarks": [
//                                {
//                                    "name": "Akcija",
//                                    "color": "FF006F",
//                                    "pictureUrl": ""
//                                }
//                            ],
//                            "frontEndStatus": [
//                                "SELLING"
//                            ],
//                            "break": false,
//                            "location": {
//                                "id": "a60de864-5c52-11ee-a81c-000d3aa868a2",
//                                "name": "Šiaulių arena",
//                                "address": "ner"
//                            },
//                            "city": {
//                                "id": 1,
//                                "name": "Vilnius",
//                                "isImportant": true
//                            },
//                            "venueAddress": "ner, Vilnius, Lithuania",
//                            "priceFrom": 100,
//                            "priceFromWithTaxes": 100,
//                            "clubTicketsAvailable": false,
//                            "metaTitle": "Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket",
//                            "promoter": {
//                                "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",
//                                "name": "Organizatorius Z, VŠĮ",
//                                "address": "Didlaukio g. 45, 08321, Vilnius, Lietuva",
//                                "companyCode": "123123789",
//                                "vatCode": "123456",
//                                "email": "testkakava@gmail.com",
//                                "phone": "+37060000001",
//                                "pictureUrl": "",
//                                "brandName": "ORGZ"
//                            },
//                            "isHourlySales": false
//                        }
//                    ],
//                    "giftVouchers": [],
//                    "eventGroups": [],
//                    "resultStatus": "OK"
//                }""";
//
//        server.stubFor(get("https://app-kkv-be-test.azurewebsites.net/api/v1/event/show").willReturn(ok(givenJson)));
//
//        ShowsResponseTO actual = client.requestShows();
//        ShowsResponseTO expected = new ShowsResponseTO(
//                List.of(
//                        new ShowTO(
//                                2331,
//                                1755,
//                                "Šiaulių arena. Šiaulių „Šiauliai“ - Mažeikių „M Basket",
//                                "<a href=\"https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\" target=\"_blank\">taisyklės</a>&nbsp;Rajoną pavadinti&nbsp;<i><b>Pilaite</b></i>&nbsp;nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui&nbsp;<i><b>Sudervėlė</b></i>. Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.&nbsp;<i><b>Pilaitė</b></i>&nbsp;– istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis&nbsp;<i><b>Pilaitė</b></i>&nbsp;ir tikrai yra stovėjusi pilis. Todėl&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\"Lenkų kalba\">lenk.</a>&nbsp;<i>Zameczek</i>&nbsp;yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.    <br>",
//                                new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2"),
//                                List.of(new CategoryTO(4)),
//                                LocalDateTime.of(2026, 9, 29, 16, 3, 0),
//                                List.of("PROMOTION"),
//                                new ShowLocationTO(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "Šiaulių arena"),
//                                "ner, Vilnius, Lithuania",
//                                100,
//                                new PromoterTO(fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"), "Organizatorius Z, VŠĮ")
//                        )
//                )
//        );
//        assertEquals(expected, actual);
//    }
//}
