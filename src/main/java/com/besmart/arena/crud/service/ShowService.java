package com.besmart.arena.crud.service;

import com.besmart.arena.crud.dto.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class ShowService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Show> shows) {
        batchUpdate(
                jdbcTemplate,
                shows,
                """
                        INSERT INTO shows(external_short_id, title, subtitle, description, category_id, venue_id, image_url)
                        VALUES(:externalShortId, :title, :subtitle, :description, :categoryId, :venueId, :imageUrl)
                        ON CONFLICT (external_short_id) DO
                        UPDATE SET
                            title = :title,
                            subtitle = :subtitle,
                            description = :description,
                            category_id = (SELECT id FROM categories WHERE external_id = :categoryExternalId),
                            venue_id = (SELECT id FROM venues WHERE external_id = :venueExternalId),
                            image_url = :imageUrl
                        WHERE tags.external_id = :externalId"""
        );
    }
}
