package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Promoter;
import com.besmart.arena.crud.domain.Provider;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static com.besmart.arena.util.ResultSetUtil.getUUID;

@Component
public final class PromoterRowMapper implements RowMapper<Promoter> {
    static final String ALIAS_ID = "promoterId";
    static final String ALIAS_EXTERNAL_ID = "promoterExternalId";
    static final String ALIAS_NAME = "promoterName";
    static final String ALIAS_ICON_URL = "promoterIconUrl";
    static final String ALIAS_WEB_PAGE_URL = "promoterWebPageUrl";
    static final String ALIAS_PROVIDER_ID = "promoterProviderId";

    @Override
    public Promoter mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        UUID externalId = getUUID(resultSet, ALIAS_EXTERNAL_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String iconUrl = resultSet.getString(ALIAS_ICON_URL);
        String webPageUrl = resultSet.getString(ALIAS_WEB_PAGE_URL);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return new Promoter(id, externalId, name, iconUrl, webPageUrl, provider);
    }
}
