//package com.besmart.arena.crud.repository;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.besmart.arena.crud.domain.Provider;
//import com.besmart.arena.crud.domain.Tag;
//import com.besmart.arena.crud.rowmapper.TagRowMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.Set;
//
//import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public final class TagRepositoryTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private TagRepository repository;
//
//    @Autowired
//    private TagRowMapper rowMapper;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void tagsShouldBeRefreshedByName() {
//        List<Tag> givenTags = List.of(
//                Tag.builder()
//                        .name("first-tag")
//                        .provider(Provider.builder().id(1L).build())
//                        .build(),
//                Tag.builder()
//                        .name("second-tag")
//                        .provider(Provider.builder().id(2L).build())
//                        .build()
//        );
//
//        repository.refreshByName(givenTags);
//
//        Set<Tag> actual = findAllTags();
//        Set<Tag> expected = Set.of(
//                new Tag(1000L, "first-tag", Provider.builder().id(1L).build()),
//                new Tag(2L, "second-tag", Provider.builder().id(2L).build()),
//                new Tag(1001L, "PROMOTION", Provider.builder().id(1L).build())
//        );
//        assertEquals(expected, actual);
//    }
//
//    private Set<Tag> findAllTags() {
//        return queryForSet(
//                jdbcTemplate,
//                rowMapper,
//                "SELECT id AS tagId, name AS tagName, provider_id AS tagProviderId FROM tags"
//        );
//    }
//}
