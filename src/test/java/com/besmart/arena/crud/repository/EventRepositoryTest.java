//package com.besmart.arena.crud.repository;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.besmart.arena.crud.domain.Event;
//import com.besmart.arena.crud.domain.Show;
//import com.besmart.arena.crud.rowmapper.EventRowMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//
//import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public final class EventRepositoryTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private EventRepository repository;
//
//    @Autowired
//    private EventRowMapper rowMapper;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void eventsShouldBeRefreshedByExternalId() {
//        List<Event> givenEvents = List.of(
//                Event.builder()
//                        .externalShortId(2000)
//                        .title("third-title")
//                        .subtitle("third-subtitle")
//                        .description("third-description")
//                        .dateTime(LocalDateTime.of(2024, 11, 20, 21, 22, 23))
//                        .show(Show.builder().externalShortId(2001).build())
//                        .build(),
//                Event.builder()
//                        .externalShortId(2002)
//                        .title("fourth-title")
//                        .subtitle("fourth-subtitle")
//                        .description("fourth-description")
//                        .dateTime(LocalDateTime.of(2024, 11, 20, 21, 22, 24))
//                        .show(Show.builder().externalShortId(2001).build())
//                        .build()
//        );
//
//        repository.refreshByExternalId(givenEvents);
//
//        Set<Event> actual = findAllEvents();
//        Set<Event> expected = Set.of(
//                new Event(
//                        1000L,
//                        2000,
//                        "third-title",
//                        "third-subtitle",
//                        "third-description",
//                        LocalDateTime.of(2024, 11, 20, 21, 22, 23),
//                        Show.builder().id(1001L).build()
//                ),
//                new Event(
//                        2L,
//                        2002,
//                        "fourth-title",
//                        "fourth-subtitle",
//                        "fourth-description",
//                        LocalDateTime.of(2024, 11, 20, 21, 22, 24),
//                        Show.builder().id(1001L).build()
//                ),
//                new Event(
//                        1001L,
//                        2001,
//                        "Dinamika",
//                        "second-subtitle",
//                        "<a href=\\\\ \"https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-20543c095f6f\\\" target=\\\\ \"_blank\\\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė  – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis Pilaitė ir tikrai yra stovėjusi pilis. Todėl <a href=\\\\ \"https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\\\" title=\\\\ \"Lenkų kalba\\\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.",
//                        LocalDateTime.of(2026, 11, 18, 13, 19, 0),
//                        Show.builder().id(1000L).build()
//                )
//        );
//        assertEquals(expected, actual);
//    }
//
//    private Set<Event> findAllEvents() {
//        return queryForSet(
//                jdbcTemplate,
//                rowMapper,
//                """
//                        SELECT
//                            id AS eventId,
//                            external_short_id AS eventExternalShortId,
//                            title AS eventTitle,
//                            subtitle AS eventSubtitle,
//                            description AS eventDescription,
//                            date_time AS eventDateTime,
//                            show_id AS eventShowId
//                        FROM events"""
//        );
//    }
//}
