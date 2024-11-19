package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class CategoryRowMapper implements RowMapper<Category> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_ID = "external_id";
    static final String COLUMN_NAME_NAME = "name";
    static final String COLUMN_NAME_PRIMARY_COLOR = "primary_color";
    static final String COLUMN_NAME_SECONDARY_COLOR = "secondary_color";

    @Override
    public Category mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        int externalId = resultSet.getInt(COLUMN_NAME_EXTERNAL_ID);
        String name = resultSet.getString(COLUMN_NAME_NAME);
        String primaryColor = resultSet.getString(COLUMN_NAME_PRIMARY_COLOR);
        String secondaryColor = resultSet.getString(COLUMN_NAME_SECONDARY_COLOR);
        return new Category(id, externalId, name, primaryColor, secondaryColor);
    }
}