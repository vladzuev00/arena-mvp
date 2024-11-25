package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class CategoryRowMapper implements RowMapper<Category> {
    static final String ALIAS_ID = "categoryId";
    static final String ALIAS_NAME = "categoryName";
    static final String ALIAS_PRIMARY_COLOR = "categoryPrimaryColor";
    static final String ALIAS_SECONDARY_COLOR = "categorySecondaryColor";

    @Override
    public Category mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String primaryColor = resultSet.getString(ALIAS_PRIMARY_COLOR);
        String secondaryColor = resultSet.getString(ALIAS_SECONDARY_COLOR);
        return new Category(id, name, primaryColor, secondaryColor);
    }
}