package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.Tag;
import com.besmart.arena.crud.rowmapper.TagRowMapper;
import com.besmart.arena.testutil.JdbcTemplateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TagRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private TagRepository repository;

    @Autowired
    private TagRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void tagsShouldBeRefreshedByExternalId() {
        List<Tag> givenTags = List.of(
                Tag.builder().externalId(2000).name("second-tag").build(),
                Tag.builder().externalId(2001).name("third-tag").build()
        );

        repository.refreshByExternalId(givenTags);

        Set<Tag> actual = findAllTags();
        Set<Tag> expected = Set.of(new Tag(1000L, 2000, "second-tag"), new Tag(2L, 2001, "third-tag"));
        assertEquals(expected, actual);
    }

    private Set<Tag> findAllTags() {
        return JdbcTemplateUtil.queryForSet(jdbcTemplate, rowMapper, "SELECT id, external_id, name FROM tags");
    }
}
