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

    public void refreshByName(List<Tag> tags) {
        batchUpdate(jdbcTemplate, tags, "INSERT INTO tags(name) VALUES(:name) ON CONFLICT (name) DO NOTHING");
    }
}
