package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Promoter;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.util.ResultSetUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;

@Component
public final class PromoterRowMapper implements RowMapper<Promoter> {
    static final String ALIAS_ID = "promoterId";
    static final String ALIAS_EXTERNAL_ID = "promoterExternalId";
    static final String ALIAS_NAME = "promoterName";
    static final String ALIAS_ADDRESS = "promoterAddress";
    static final String ALIAS_EMAIL = "promoterEmail";
    static final String ALIAS_PHONE = "promoterPhone";
    static final String ALIAS_IMAGE_URL = "promoterImageUrl";
    static final String ALIAS_EXTERNAL_URL = "promoterExternalUrl";
    static final String ALIAS_PROVIDER_ID = "promoterProviderId";

    @Override
    public Promoter mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String externalId = resultSet.getString(ALIAS_EXTERNAL_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String address = resultSet.getString(ALIAS_ADDRESS);
        String email = resultSet.getString(ALIAS_EMAIL);
        String phone = resultSet.getString(ALIAS_PHONE);
        String imageUrl = resultSet.getString(ALIAS_IMAGE_URL);
        String externalUrl = resultSet.getString(ALIAS_EXTERNAL_URL);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return new Promoter(id, externalId, name, address, email, phone, imageUrl, externalUrl, provider);
    }
}
