package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.jdbc.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class CategoryServiceTest extends AbstractSpringBootTest {

    @Autowired
    private CategoryService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Sql(statements = "INSERT INTO categories(external_id, name, primary_color, secondary_color) VALUES(255, 'first-category', '#90caef', '#faa538')")
    public void categoriesShouldBeRefreshedByExternalId() {
        List<Category> givenCategories = List.of(
                Category.builder()
                        .externalId(255)
                        .name("second-category")
                        .primaryColor("#b07289")
                        .secondaryColor("#e00e79")
                        .build(),
                Category.builder()
                        .externalId(256)
                        .name("third-category")
                        .primaryColor("#3d4761")
                        .secondaryColor("#1f2431")
                        .build()
        );

        service.refreshByExternalId(givenCategories);

        Set<Category> actual = findAllCategories();
        Set<Category> expected = Set.of(
                new Category(1L, 255, "second-category", "#b07289", "#e00e79"),
                new Category(3L, 256, "third-category", "#3d4761", "#1f2431")
        );
        assertEquals(expected, actual);
    }

    private Set<Category> findAllCategories() {
        try (var stream = jdbcTemplate.queryForStream("SELECT id, external_id, name, primary_color, secondary_color FROM categories", new CategoryRowMapper())) {
            return stream.collect(toUnmodifiableSet());
        }
    }

    private static final class CategoryRowMapper implements RowMapper<Category> {
        private static final String COLUMN_NAME_ID = "id";
        private static final String COLUMN_NAME_EXTERNAL_ID = "external_id";
        private static final String COLUMN_NAME_NAME = "name";
        private static final String COLUMN_NAME_PRIMARY_COLOR = "primary_color";
        private static final String COLUMN_NAME_SECONDARY_COLOR = "secondary_color";

        @Override
        public Category mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            long id = resultSet.getLong(COLUMN_NAME_ID);
            int externalId = resultSet.getInt(COLUMN_NAME_EXTERNAL_ID);
            String name = resultSet.getString(COLUMN_NAME_NAME);
            String primaryColor = resultSet.getString(COLUMN_NAME_PRIMARY_COLOR);
            String secondaryColor = resultSet.getString(COLUMN_NAME_SECONDARY_COLOR);
            return new Category(id, externalId, name, primaryColor, secondaryColor);
        }
    }
}
