package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class TagRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Tag> tags) {
        batchUpdate(
                jdbcTemplate,
                tags,
                """
                        INSERT INTO tags(external_id, name) VALUES(:externalId, :name)
                        ON CONFLICT (external_id) DO
                        UPDATE SET name = :name WHERE tags.external_id = :externalId"""
        );
    }
}
