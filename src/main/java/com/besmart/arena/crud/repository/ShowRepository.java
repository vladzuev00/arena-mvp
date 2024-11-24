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
                        CALL refresh_show(
                            :externalShortId,
                            :title,
                            :subtitle,
                            :description,
                            :venue.externalId,
                            :imageUrl,
                            :promoter.externalId,
                            :categoryExternalIds,
                            :tagNames)"""
        );
    }
}
