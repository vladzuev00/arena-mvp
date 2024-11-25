package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;

//TODO: correct test
@Component
public final class TagRowMapper implements RowMapper<Tag> {
    static final String ALIAS_ID = "tagId";
    static final String ALIAS_NAME = "tagName";
    static final String ALIAS_PROVIDER_ID = "tagProviderId";

    @Override
    public Tag mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return new Tag(id, name, provider);
    }
}
