package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Provider;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class ProviderRowMapper implements RowMapper<Provider> {
    static final String ALIAS_ID = "providerId";
    static final String ALIAS_NAME = "providerName";

    @Override
    public Provider mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        return new Provider(id, name);
    }
}
