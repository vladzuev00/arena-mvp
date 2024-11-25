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

    public void refreshByExternalId(List<Venue> venues) {
        batchUpdate(
                jdbcTemplate,
                venues,
                """
                        INSERT INTO venues(external_id, name, address, latitude, longitude, provider_id) VALUES(:externalId, :name, :address, :latitude, :longitude, :provider.id)
                        ON CONFLICT (external_id, provider_id) DO
                        UPDATE SET name = :name, address = :address, latitude = :latitude, longitude = :longitude, provider_id = :provider.id
                        WHERE venues.external_id = :externalId AND venues.provider_id = :provider.id"""
        );
    }
}
