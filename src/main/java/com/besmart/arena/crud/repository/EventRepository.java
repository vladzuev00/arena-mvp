package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class EventRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Event> events) {
        batchUpdate(
                jdbcTemplate,
                events,
                """
                        INSERT INTO events(external_short_id, title, subtitle, description, date_time, show_id)
                        VALUES(:externalShortId, :title, :subtitle, :description, :dateTime, (SELECT id FROM shows WHERE external_short_id = :show.externalShortId))
                        ON CONFLICT (external_short_id) DO
                        UPDATE SET
                            title = :title,
                            subtitle = :subtitle,
                            description = :description,
                            date_time = :dateTime,
                            show_id = (SELECT id FROM shows WHERE external_short_id = :show.externalShortId)
                        WHERE events.external_short_id = :externalShortId"""
        );
    }
}
