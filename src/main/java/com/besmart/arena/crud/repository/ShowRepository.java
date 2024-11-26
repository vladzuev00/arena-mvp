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
                        WHERE shows.external_short_id = :externalShortId AND shows.provider_id = :provider.id;
                        
                        DELETE FROM shows_categories WHERE show_id = (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id);
                        INSERT INTO shows_categories(show_id, category_id)
                        SELECT refreshed_show_id, id FROM categories
                        WHERE name = ANY(:categoryNames);
                        
                        DELETE FROM shows_tags WHERE show_id = (SELECT id FROM shows WHERE external_short_id = :externalShortId AND provider_id = :provider.id);
                        INSERT INTO shows_tags(show_id, tag_id)
                        SELECT refreshed_show_id, id FROM tags
                        WHERE name = ANY(:tagNames);"""
        );
    }
}
