package com.besmart.arena.client.domain;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.UUID.fromString;

public final class ShowTOTest extends AbstractSpringBootTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void showShouldBeSerializedToJson()
            throws Exception {
        ShowTO givenShow = new ShowTO(
                2341,
                1763,
                "Dinamika",
                "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Fizika\" title=\"Fizika\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\" title=\"Dekarto koordinačių sistema\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
                new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"),
                List.of(new CategoryTO(3)),
                LocalDateTime.of(2026, 11, 18, 13, 19, 0),
                List.of("PROMOTION"),
                new ShowLocationTO("Šiaulių arena"),
                "ner, Vilnius, Lithuania",
                new PromoterTO("Organizatorius Z, VŠĮ")
        );

        String actual = objectMapper.writeValueAsString(givenShow);
        String expected = """
                {
                   "shortId": 2341,
                   "eventShortId": 1763,
                   "eventTitle": "Dinamika",
                   "eventDescription": "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Fizika\\" title=\\"Fizika\\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\\" title=\\"Dekarto koordinačių sistema\\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
                   "eventPicture": {
                     "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"
                   },
                   "eventCategories": [
                     {
                       "id": 3
                     }
                   ],
                   "startDateTime": "2026-11-18T13:19:00",
                   "marks": [
                     "PROMOTION"
                   ],
                   "location": {
                     "name": "Šiaulių arena"
                   },
                   "venueAddress": "ner, Vilnius, Lithuania",
                   "promoter": {
                     "name": "Organizatorius Z, VŠĮ"
                   }
                }""";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void showShouldBeDeserializedFromJson()
            throws Exception {
        String givenJson = """
                {
                   "id": "d1ad0c6b-9d97-11ee-a81c-6045bd87e447",
                   "shortId": 2341,
                   "eventId": "4edd91c2-9d97-11ee-a81c-6045bd87e447",
                   "eventShortId": 1763,
                   "eventTitle": "Dinamika",
                   "eventTitleForAnalytics": "Dinamika",  не надо
                   "eventTitleSlug": "dinamika",  не надо
                   "eventTitleSlugs": [   не надо
                     {
                       "language": "en",
                       "value": "dinamika"
                     },
                     {
                       "language": "lt",
                       "value": "dinamika"
                     }
                   ],
                   "hasEnglishDescription": false,  не надо
                   "eventDescription": "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Fizika\\" title=\\"Fizika\\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\\" title=\\"Dekarto koordinačių sistema\\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
                   "eventPicture": {
                     "desktopPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447",
                     "mobilePictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447", не надо 
                     "coverPictureUrl": "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"  не надо 
                   },
                   "eventCategories": [
                     {
                       "id": 3   
                     }
                   ],
                   "state": "Selling",     
                   "startDateTime": "2026-11-18T13:19:00.0000000",
                   "endDateTime": "2026-12-18T13:19:00.0000000",
                   "showDate": true,    не надо
                   "distributionStartDateTime": "2023-12-18T13:40:00.0000000",    не надо
                   "showPeriod": false, не надо
                   "duration": "1 men",   
                   "marks": [   
                     "PROMOTION"
                   ],
                   "customMarks": [
                     {
                       "name": "Akcija",  в теги
                       "color": "FF006F",  не надо
                       "pictureUrl": ""  не надо
                     }
                   ],
                   "frontEndStatus": [  в теги
                     "SELLING"
                   ],
                   "break": false,    
                   "location": {          не надо
                     "id": "a60de864-5c52-11ee-a81c-000d3aa868a2",
                     "name": "Šiaulių arena",
                     "address": "ner"
                   },
                   "city": {     не надо
                     "id": 1,
                     "name": "Vilnius",
                     "isImportant": true
                   },
                   "venueAddress": "ner, Vilnius, Lithuania",     нет
                   "priceFrom": 2100,   
                   "priceFromWithTaxes": 2100,      
                   "clubTicketsAvailable": false,
                   "metaTitle": "Dinamika",     нет
                   "promoter": {
                     "id": "11aa329a-44a6-11ed-a81c-000d3a29937e",      
                     "name": "Organizatorius Z, VŠĮ", 
                     "address": "Didlaukio g. 45, 08321, Vilnius, Lietuva",     
                     "companyCode": "123123789",    нет
                     "vatCode": "123456",      нет
                     "email": "testkakava@gmail.com",     
                     "phone": "+37060000001",    
                     "pictureUrl": "",
                     "brandName": "ORGZ" 
                   },
                   "isHourlySales": false нет
                }""";

        ShowTO actual = objectMapper.readValue(givenJson, ShowTO.class);
        ShowTO expected = new ShowTO(
                2341,
                1763,
                "Dinamika",
                "    <b>Mechanika</b>&nbsp;–&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Fizika\" title=\"Fizika\">fizikos</a>&nbsp;skyrius, nagrinėjantis paprasčiausią materijos judėjimo formą&nbsp;–&nbsp;<b>mechaninį judėjimą</b>&nbsp;(kūno padėties kitimą kitų kūnų atžvilgiu). Nagrinėjant judėjimą, pasirenkamas&nbsp;<b>atskaitos kūnas</b>, kurio atžvilgiu nagrinėjamas kito kūno (reliatyvus) judėjimas. Su atskaitos kūnu susieta koordinačių sistema&nbsp;–&nbsp;<b>atskaitos sistema</b>, paprasčiausia&nbsp;– stačiakampė&nbsp;<a href=\"https://lt.wikipedia.org/wiki/Dekarto_koordina%C4%8Di%C5%B3_sistema\" title=\"Dekarto koordinačių sistema\">Dekarto koordinačių sistema</a>. Dažnai kūno padėtis nagrinėjama kaip materialiojo taško M padėtis, apibūdinama koordinatėmis&nbsp;<b>xM</b>,&nbsp;<b>yM</b>&nbsp;ir&nbsp;<b>zM</b>&nbsp;arba spinduliu vektoriumi:    <br>",
                new EventPictureTO("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/4edd91c4-9d97-11ee-a81c-6045bd87e447"),
                List.of(new CategoryTO(3)),
                LocalDateTime.of(2026, 11, 18, 13, 19, 0),
                List.of("PROMOTION"),
                new ShowLocationTO("Šiaulių arena"),
                "ner, Vilnius, Lithuania",
                new PromoterTO("Organizatorius Z, VŠĮ")
        );
        Assertions.assertEquals(expected, actual);
    }
}
