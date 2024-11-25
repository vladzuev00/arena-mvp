package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class TagRowMapper implements RowMapper<Tag> {
    static final String ALIAS_ID = "tagId";
    static final String ALIAS_NAME = "tagName";

    @Override
    public Tag mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        return new Tag(id, name);
    }
}
