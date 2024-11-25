package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Venue;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class VenueRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByName(List<Venue> venues) {
        batchUpdate(
                jdbcTemplate,
                venues,
                """
                        INSERT INTO venues(name, address, latitude, longitude) VALUES(:name, :address, :latitude, :longitude)
                        ON CONFLICT (name) DO
                        UPDATE SET address = :address, latitude = :latitude, longitude = :longitude WHERE venues.name = :name"""
        );
    }
}
