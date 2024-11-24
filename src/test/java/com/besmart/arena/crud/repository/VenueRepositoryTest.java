package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.Venue;
import com.besmart.arena.crud.rowmapper.VenueRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class VenueRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private VenueRepository repository;

    @Autowired
    private VenueRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void venuesShouldBeRefreshedByExternalId() {
        List<Venue> givenVenues = List.of(
                Venue.builder()
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"))
                        .name("second-name")
                        .address("second-address")
                        .latitude(9.9)
                        .longitude(10.1)
                        .build(),
                Venue.builder()
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a4"))
                        .name("third-name")
                        .address("third-address")
                        .latitude(10.2)
                        .longitude(10.3)
                        .build()
        );

        repository.refreshByExternalId(givenVenues);

        Set<Venue> actual = findAllVenues();
        Set<Venue> expected = Set.of(
                new Venue(
                        1001L,
                        fromString("a60de864-5c52-11ee-a81c-000d3aa868a3"),
                        "Šiaulių arena",
                        "ner, Vilnius, Lithuania",
                        7.7,
                        8.8
                ),
                new Venue(
                        1000L,
                        fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"),
                        "second-name",
                        "second-address",
                        9.9,
                        10.1
                ),
                new Venue(
                        2L,
                        fromString("a60de864-5c52-11ee-a81c-000d3aa868a4"),
                        "third-name",
                        "third-address",
                        10.2,
                        10.3
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Venue> findAllVenues() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                """
                        SELECT
                            id AS venueId,
                            external_id AS venueExternalId,
                            name AS venueName,
                            address AS venueAddress,
                            latitude AS venueLatitude,
                            longitude AS venueLongitude
                        FROM venues"""
        );
    }
}
