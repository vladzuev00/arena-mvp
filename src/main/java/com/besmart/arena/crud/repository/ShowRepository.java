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
                        INSERT INTO shows(external_short_id, title, subtitle, description, venue_id, image_url, promoter_id, provider_id)
                        VALUES(
                            :externalShortId,
                            :title,
                            :subtitle,
                            :description,
                            (SELECT id FROM venues WHERE name = :venue.name),
                            :imageUrl,
                            (SELECT id FROM promoters WHERE name = :promoter.name),
                            :provider.id
                        )
                        ON CONFLICT (external_short_id, provider_id) DO
                        UPDATE SET
                            title = :title,
                            subtitle = :subtitle,
                            description = :description,
                            venue_id = (SELECT id FROM venues WHERE name = :venue.name),
                            image_url = :imageUrl,
                            promoter_id = (SELECT id FROM promoters WHERE name = :promoter.name)
                        WHERE shows.external_short_id = :externalShortId AND shows.provider_id = :provider.id"""
        );
        batchUpdate(
                jdbcTemplate,
                shows,
                "DELETE FROM shows_categories WHERE show_id = (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id)"
        );
        batchUpdate(
                jdbcTemplate,
                shows,
                """
                        INSERT INTO shows_categories(show_id, category_id)
                        SELECT DISTINCT refreshed_show_id.id, categories.id FROM categories, (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id) AS refreshed_show_id
                        WHERE name = ANY(:categoryNames)"""
        );
        batchUpdate(
                jdbcTemplate,
                shows,
                "DELETE FROM shows_tags WHERE show_id = (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id)"
        );
        batchUpdate(
                jdbcTemplate,
                shows,
                """
                        INSERT INTO shows_tags(show_id, tag_id)
                        SELECT DISTINCT refreshed_show_id.id, tags.id FROM tags, (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id) AS refreshed_show_id
                        WHERE name = ANY(:tagNames)"""
        );
    }
}
