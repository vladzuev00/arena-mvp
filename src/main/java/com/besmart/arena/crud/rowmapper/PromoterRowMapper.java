package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Promoter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class PromoterRowMapper implements RowMapper<Promoter> {
    static final String ALIAS_ID = "promoterId";
    static final String ALIAS_NAME = "promoterName";
    static final String ALIAS_ICON_URL = "promoterIconUrl";
    static final String ALIAS_WEB_PAGE_URL = "promoterWebPageUrl";

    @Override
    public Promoter mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String iconUrl = resultSet.getString(ALIAS_ICON_URL);
        String webPageUrl = resultSet.getString(ALIAS_WEB_PAGE_URL);
        return null;
//        return new Promoter(id, name, iconUrl, webPageUrl);
    }
}
