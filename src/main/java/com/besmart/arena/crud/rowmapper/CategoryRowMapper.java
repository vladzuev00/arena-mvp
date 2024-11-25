package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Category;
import com.besmart.arena.crud.domain.Provider;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;

@Component
public final class CategoryRowMapper implements RowMapper<Category> {
    static final String ALIAS_ID = "categoryId";
    static final String ALIAS_EXTERNAL_ID = "categoryExternalId";
    static final String ALIAS_NAME = "categoryName";
    static final String ALIAS_PRIMARY_COLOR = "categoryPrimaryColor";
    static final String ALIAS_SECONDARY_COLOR = "categorySecondaryColor";
    static final String ALIAS_PROVIDER_ID = "categoryProviderId";

    @Override
    public Category mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        int externalId = resultSet.getInt(ALIAS_EXTERNAL_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String primaryColor = resultSet.getString(ALIAS_PRIMARY_COLOR);
        String secondaryColor = resultSet.getString(ALIAS_SECONDARY_COLOR);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return new Category(id, externalId, name, primaryColor, secondaryColor, provider);
    }
}