//package com.besmart.arena.crud.repository;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.besmart.arena.crud.domain.Category;
//import com.besmart.arena.crud.domain.Provider;
//import com.besmart.arena.crud.rowmapper.CategoryRowMapper;
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
//public final class CategoryRepositoryTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private CategoryRepository repository;
//
//    @Autowired
//    private CategoryRowMapper rowMapper;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void categoriesShouldBeRefreshedByExternalId() {
//        List<Category> givenCategories = List.of(
//                Category.builder()
//                        .externalId(2000)
//                        .name("third-category")
//                        .primaryColor("#b07289")
//                        .secondaryColor("#e00e79")
//                        .provider(Provider.builder().id(1L).build())
//                        .build(),
//                Category.builder()
//                        .externalId(2002)
//                        .name("fourth-category")
//                        .primaryColor("#3d4761")
//                        .secondaryColor("#1f2431")
//                        .provider(Provider.builder().id(2L).build())
//                        .build()
//        );
//
//        repository.refreshByExternalId(givenCategories);
//
//        Set<Category> actual = findAllCategories();
//        Set<Category> expected = Set.of(
//                new Category(1001L, 2001, "second-category", "#FFFFFFAA", "#56135a", Provider.builder().id(1L).build()),
//                new Category(2L, 2002, "fourth-category", "#3d4761", "#1f2431", Provider.builder().id(2L).build()),
//                new Category(1000L, 2000, "third-category", "#b07289", "#e00e79", Provider.builder().id(1L).build())
//        );
//        assertEquals(expected, actual);
//    }
//
//    private Set<Category> findAllCategories() {
//        return queryForSet(
//                jdbcTemplate,
//                rowMapper,
//                """
//                        SELECT
//                            id AS categoryId,
//                            external_id AS categoryExternalId,
//                            name AS categoryName,
//                            primary_color AS categoryPrimaryColor,
//                            secondary_color AS categorySecondaryColor,
//                            provider_id AS categoryProviderId
//                        FROM categories"""
//        );
//    }
//}
