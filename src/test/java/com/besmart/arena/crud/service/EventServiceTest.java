package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Event;
import com.besmart.arena.crud.dto.Show;
import com.besmart.arena.crud.rowmapper.EventRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class EventServiceTest extends AbstractSpringBootTest {

    @Autowired
    private EventService service;

    @Autowired
    private EventRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void eventsShouldBeRefreshedByExternalId() {
        List<Event> givenEvents = List.of(
                Event.builder()
                        .externalShortId(2000)
                        .title("second-title")
                        .subtitle("second-subtitle")
                        .description("second-description")
                        .dateTime(LocalDateTime.of(2024, 11, 20, 21, 22, 23))
                        .show(Show.builder().externalShortId(2001).build())
                        .build(),
                Event.builder()
                        .externalShortId(2001)
                        .title("third-title")
                        .subtitle("third-subtitle")
                        .description("third-description")
                        .dateTime(LocalDateTime.of(2024, 11, 20, 21, 22, 24))
                        .show(Show.builder().externalShortId(2001).build())
                        .build()
        );

        service.refreshByExternalId(givenEvents);

        Set<Event> actual = findAllEvents();
        Set<Event> expected = Set.of(
                new Event(
                        2L,
                        2001,
                        "third-title",
                        "third-subtitle",
                        "third-description",
                        LocalDateTime.of(2024, 11, 20, 21, 22, 24),
                        Show.builder().id(1001L).build()
                ),
                new Event(
                        1000L,
                        2000,
                        "second-title",
                        "second-subtitle",
                        "second-description",
                        LocalDateTime.of(2024, 11, 20, 21, 22, 23),
                        Show.builder().id(1001L).build()
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Event> findAllEvents() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                "SELECT id, external_short_id, title, subtitle, description, date_time, show_id FROM events"
        );
    }
}
