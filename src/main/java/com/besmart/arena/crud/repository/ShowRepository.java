package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class ShowRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Show> shows) {
        batchUpdate(
                jdbcTemplate,
                shows,
                """
                        INSERT INTO shows(external_short_id, title, subtitle, description, venue_id, image_url)
                        VALUES(:externalShortId, :title, :subtitle, :description, (SELECT id FROM venues WHERE external_id = :venue.externalId), :imageUrl)
                        ON CONFLICT (external_short_id) DO
                        UPDATE SET
                            title = :title,
                            subtitle = :subtitle,
                            description = :description,
                            venue_id = (SELECT id FROM venues WHERE external_id = :venue.externalId),
                            image_url = :imageUrl
                        WHERE shows.external_short_id = :externalShortId"""
        );
    }
}
