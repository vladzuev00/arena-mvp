package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class CategoryRowMapper implements RowMapper<Category> {
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