//package com.besmart.arena.domain;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//
//import static com.besmart.arena.client.domain.ShowMark.PROMOTION;
//import static java.util.UUID.fromString;
//
//public final class ShowTOTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void showShouldBeSerializedToJson()
//            throws Exception {
//        ShowTO givenShow = new ShowTO(
//                2341,
//                1763,
//                "Dinamika",
//                "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Fizika\" title=\"Fizika\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\" title=\"Dekarto koordinačių sistema\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
//                new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"),
//                new CategoryTO[]{new CategoryTO(3)},
//                LocalDateTime.of(2026, 11, 18, 13, 19, 0),
//                new ShowMark[]{PROMOTION},
//                new ShowCustomMarkTO[]{new ShowCustomMarkTO("Akcija", "FF006F", "")},
//                new ShowLocationTO(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "Šiaulių arena", "ner"),
//                "ner, Vilnius, Lithuania",
//                2100,
//                new PromoterTO(
//                        fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"),
//                        "Organizatorius Z, VŠĮ",
//                        "Didlaukio g. 45, 08321, Vilnius, Lietuva",
//                        "testkakava@gmail.com",
//                        "+37060000001",
//                        ""
//                )
//        );
//
//        String actual = objectMapper.writeValueAsString(givenShow);
//        String expected = """
//                {
//                  "shortId": 2341,
//                  "eventShortId": 1763,
//                  "eventTitle": "Dinamika",
//                  "eventPicture": {
//                    "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"
//                  },
//                  "eventCategories": [
//                    {
//                      "id": 3
//                    }
//                  ],
//                  "startDateTime": "2026-11-18T13:19:00",
//                  "marks": [
//                    "PROMOTION"
//                  ],
//                  "customMarks": [
//                    {
//                      "name": "Akcija",
//                      "color": "FF006F",
//                      "pictureUrl": ""
//                    }
//                  ],
//                  "location": {
//                    "id": "a60de864-5c52-11ee-a81c-000d3aa868a2",
//                    "name": "Šiaulių arena",
//                    "address": "ner"
//                  },
//                  "venueAddress": "ner, Vilnius, Lithuania",
//                  "priceFrom": 2100,
//                  "promoter": {
//                    "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",
//                    "name": "Organizatorius Z, VŠĮ",
//                    "address": "Didlaukio g. 45, 08321, Vilnius, Lietuva",
//                    "email": "testkakava@gmail.com",
//                    "pictureUrl": "",
//                    "phoneNumber": "+37060000001"
//                  },
//                  "eventDescriptionHtml": "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Fizika\\" title=\\"Fizika\\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\\" title=\\"Dekarto koordinačių sistema\\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>"
//                }""";
//        JSONAssert.assertEquals(expected, actual, true);
//    }
//
//    @Test
//    public void showShouldBeDeserializedFromJson()
//            throws Exception {
//        String givenJson = """
//                {
//                   "id": "d1ad0c6b-9d97-11ee-a81c-6045bd87e447",
//                   "shortId": 2341,
//                   "eventId": "4edd91c2-9d97-11ee-a81c-6045bd87e447",
//                   "eventShortId": 1763,
//                   "eventTitle": "Dinamika",
//                   "eventTitleForAnalytics": "Dinamika",
//                   "eventTitleSlug": "dinamika",
//                   "eventTitleSlugs": [
//                     {
//                       "language": "en",
//                       "value": "dinamika"
//                     },
//                     {
//                       "language": "lt",
//                       "value": "dinamika"
//                     }
//                   ],
//                   "hasEnglishDescription": false,
//                   "eventDescription": "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Fizika\\" title=\\"Fizika\\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\\" title=\\"Dekarto koordinačių sistema\\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
//                   "eventPicture": {
//                     "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447",
//                     "mobilePictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447",
//                     "coverPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"
//                   },
//                   "eventCategories": [
//                     {
//                       "id": 3
//                     }
//                   ],
//                   "state": "Selling",
//                   "startDateTime": "2026-11-18T13:19:00.0000000",
//                   "endDateTime": "2026-12-18T13:19:00.0000000",
//                   "showDate": true,
//                   "distributionStartDateTime": "2023-12-18T13:40:00.0000000",
//                   "showPeriod": false,
//                   "duration": "1 men",
//                   "marks": [
//                     "PROMOTION"
//                   ],
//                   "customMarks": [
//                     {
//                       "name": "Akcija",
//                       "color": "FF006F",
//                       "pictureUrl": ""
//                     }
//                   ],
//                   "frontEndStatus": [
//                     "SELLING"
//                   ],
//                   "break": false,
//                   "location": {
//                     "id": "a60de864-5c52-11ee-a81c-000d3aa868a2",
//                     "name": "Šiaulių arena",
//                     "address": "ner"
//                   },
//                   "city": {
//                     "id": 1,
//                     "name": "Vilnius",
//                     "isImportant": true
//                   },
//                   "venueAddress": "ner, Vilnius, Lithuania",
//                   "priceFrom": 2100,
//                   "priceFromWithTaxes": 2100,
//                   "clubTicketsAvailable": false,
//                   "metaTitle": "Dinamika",
//                   "promoter": {
//                     "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",
//                     "name": "Organizatorius Z, VŠĮ",
//                     "address": "Didlaukio g. 45, 08321, Vilnius, Lietuva",
//                     "companyCode": "123123789",
//                     "vatCode": "123456",
//                     "email": "testkakava@gmail.com",
//                     "phone": "+37060000001",
//                     "pictureUrl": "",
//                     "brandName": "ORGZ"
//                   },
//                   "isHourlySales": false
//                }""";
//
//        ShowTO actual = objectMapper.readValue(givenJson, ShowTO.class);
//        ShowTO expected = new ShowTO(
//                2341,
//                1763,
//                "Dinamika",
//                "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Fizika\" title=\"Fizika\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\" title=\"Dekarto koordinačių sistema\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
//                new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"),
//                new CategoryTO[]{new CategoryTO(3)},
//                LocalDateTime.of(2026, 11, 18, 13, 19, 0),
//                new ShowMark[]{PROMOTION},
//                new ShowCustomMarkTO[]{new ShowCustomMarkTO("Akcija", "FF006F", "")},
//                new ShowLocationTO(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"), "Šiaulių arena", "ner"),
//                "ner, Vilnius, Lithuania",
//                2100,
//                new PromoterTO(
//                        fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"),
//                        "Organizatorius Z, VŠĮ",
//                        "Didlaukio g. 45, 08321, Vilnius, Lietuva",
//                        "testkakava@gmail.com",
//                        "+37060000001",
//                        ""
//                )
//        );
//        Assertions.assertEquals(expected, actual);
//    }
//}
