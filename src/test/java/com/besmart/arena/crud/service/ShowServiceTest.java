package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Category;
import com.besmart.arena.crud.dto.Show;
import com.besmart.arena.crud.dto.Venue;
import com.besmart.arena.crud.rowmapper.ShowRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ShowServiceTest extends AbstractSpringBootTest {

    @Autowired
    private ShowService service;

    @Autowired
    private ShowRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void showsShouldBeRefreshedByExternalId() {
        List<Show> givenShows = List.of(
                Show.builder()
                        .externalShortId(255)
                        .title("second-title")
                        .subtitle("second-subtitle")
                        .description("second-description")
                        .category(Category.builder().id(129L).build())
                        .venue(Venue.builder().id(132L).build())
                        .build(),
                Show.builder()
                        .externalShortId(255)
                        .title("second-title")
                        .subtitle("second-subtitle")
                        .description("second-description")
                        .category(Category.builder().id(129L).build())
                        .venue(Venue.builder().id(132L).build())
                        .build()
        );

        service.refreshByExternalId(givenShows);

        Set<Show> actual = findAllShows();
        Set<Show> expected = Set.of();
        assertEquals(expected, actual);
    }

    private Set<Show> findAllShows() {
        try (var stream = jdbcTemplate.queryForStream("SELECT id, external_short_id, title, subtitle, description, category_id, venue_id, image_url FROM shows", rowMapper)) {
            return stream.collect(toUnmodifiableSet());
        }
    }
}
