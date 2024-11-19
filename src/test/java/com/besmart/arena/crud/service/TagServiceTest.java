package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Tag;
import com.besmart.arena.crud.rowmapper.TagRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TagServiceTest extends AbstractSpringBootTest {

    @Autowired
    private TagService service;

    @Autowired
    private TagRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql(statements = "INSERT INTO tags(external_id, name) VALUES(255, 'first-tag')")
    public void tagsShouldBeRefreshedByExternalId() {
        List<Tag> givenTags = List.of(
                Tag.builder()
                        .externalId(255)
                        .name("second-tag")
                        .build(),
                Tag.builder()
                        .externalId(256)
                        .name("third-tag")
                        .build()
        );

        service.refreshByExternalId(givenTags);

        Set<Tag> actual = findAllTags();
        Set<Tag> expected = Set.of(
                new Tag(1L, 255, "second-tag"),
                new Tag(3L, 256, "third-tag")
        );
        assertEquals(expected, actual);
    }

    private Set<Tag> findAllTags() {
        try (var stream = jdbcTemplate.queryForStream("SELECT id, external_id, name FROM tags", rowMapper)) {
            return stream.collect(toUnmodifiableSet());
        }
    }
}
