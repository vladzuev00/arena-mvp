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
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class VenueRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private VenueRepository repository;

    @Autowired
    private VenueRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void venuesShouldBeRefreshedByName() {
        List<Venue> givenVenues = List.of(
                Venue.builder()
                        .name("first-venue")
                        .address("second-address")
                        .latitude(9.9)
                        .longitude(10.1)
                        .build(),
                Venue.builder()
                        .name("third-venue")
                        .address("third-address")
                        .latitude(10.2)
                        .longitude(10.3)
                        .build()
        );

        repository.refreshByName(givenVenues);

        Set<Venue> actual = findAllVenues();
        Set<Venue> expected = Set.of(
                new Venue(2L, "third-venue", "third-address", 10.2, 10.3),
                new Venue(1001L, "Šiaulių arena", "ner, Vilnius, Lithuania", 7.7, 8.8),
                new Venue(1000L, "first-venue", "second-address", 9.9, 10.1)
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
                            name AS venueName,
                            address AS venueAddress,
                            latitude AS venueLatitude,
                            longitude AS venueLongitude
                        FROM venues"""
        );
    }
}
