package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Promoter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.besmart.arena.util.ResultSetUtil.getUUID;

@Component
public final class PromoterRowMapper implements RowMapper<Promoter> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_ID = "external_id";
    static final String COLUMN_NAME_NAME = "name";
    static final String COLUMN_NAME_ICON_URL = "icon_url";
    static final String COLUMN_NAME_WEB_PAGE_URL = "web_page_url";

    @Override
    public Promoter mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        UUID externalId = getUUID(resultSet, COLUMN_NAME_EXTERNAL_ID);
        String name = resultSet.getString(COLUMN_NAME_NAME);
        String iconUrl = resultSet.getString(COLUMN_NAME_ICON_URL);
        String webPageUrl = resultSet.getString(COLUMN_NAME_WEB_PAGE_URL);
        return new Promoter(id, externalId, name, iconUrl, webPageUrl);
    }
}
