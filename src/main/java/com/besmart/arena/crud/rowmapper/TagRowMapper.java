package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class TagRowMapper implements RowMapper<Tag> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_ID = "external_id";
    static final String COLUMN_NAME_NAME = "name";

    @Override
    public Tag mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        int externalId = resultSet.getInt(COLUMN_NAME_EXTERNAL_ID);
        String name = resultSet.getString(COLUMN_NAME_NAME);
        return new Tag(id, externalId, name);
    }
}
