package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Venue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.besmart.arena.util.ResultSetUtil.getUUID;

@Component
public final class VenueRowMapper implements RowMapper<Venue> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_ID = "external_id";
    static final String COLUMN_NAME_NAME = "name";
    static final String COLUMN_NAME_ADDRESS = "address";
    static final String COLUMN_NAME_LATITUDE = "latitude";
    static final String COLUMN_NAME_LONGITUDE = "longitude";

    @Override
    public Venue mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        UUID externalId = getUUID(resultSet, COLUMN_NAME_EXTERNAL_ID);
        String name = resultSet.getString(COLUMN_NAME_NAME);
        String address = resultSet.getString(COLUMN_NAME_ADDRESS);
        double latitude = resultSet.getDouble(COLUMN_NAME_LATITUDE);
        double longitude = resultSet.getDouble(COLUMN_NAME_LONGITUDE);
        return new Venue(id, externalId, name, address, latitude, longitude);
    }
}
