package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Venue;
import com.besmart.arena.crud.rowmapper.VenueRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

import static java.util.UUID.fromString;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class VenueServiceTest extends AbstractSpringBootTest {

    @Autowired
    private VenueService service;

    @Autowired
    private VenueRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void venuesShouldBeRefreshedByExternalId() {
        List<Venue> givenVenues = List.of(
                Venue.builder()
                        .externalId(fromString("550e8400-e29b-41d4-a716-446655440000"))
                        .name("third-name")
                        .address("third-address")
                        .latitude(9.9)
                        .longitude(10.1)
                        .build(),
                Venue.builder()
                        .externalId(fromString("550e8400-e29b-41d4-a716-446655440002"))
                        .name("fourth-name")
                        .address("fourth-address")
                        .latitude(10.2)
                        .longitude(10.3)
                        .build()
        );

        service.refreshByExternalId(givenVenues);

        Set<Venue> actual = findAllVenues();
        Set<Venue> expected = Set.of(
                new Venue(
                        1001L,
                        fromString("550e8400-e29b-41d4-a716-446655440001"),
                        "second-name",
                        "second-address",
                        7.7,
                        8.8
                ),
                new Venue(
                        2L,
                        fromString("550e8400-e29b-41d4-a716-446655440002"),
                        "fourth-name",
                        "fourth-address",
                        10.2,
                        10.3
                ),
                new Venue(
                        1000L,
                        fromString("550e8400-e29b-41d4-a716-446655440000"),
                        "third-name",
                        "third-address",
                        9.9,
                        10.1
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Venue> findAllVenues() {
        try (var stream = jdbcTemplate.queryForStream("SELECT id, external_id, name, address, latitude, longitude FROM venues", rowMapper)) {
            return stream.collect(toUnmodifiableSet());
        }
    }
}
