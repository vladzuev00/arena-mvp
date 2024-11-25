package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Venue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class VenueRowMapper implements RowMapper<Venue> {
    static final String ALIAS_ID = "venueId";
    static final String ALIAS_NAME = "venueName";
    static final String ALIAS_ADDRESS = "venueAddress";
    static final String ALIAS_LATITUDE = "venueLatitude";
    static final String ALIAS_LONGITUDE = "venueLongitude";

    @Override
    public Venue mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String address = resultSet.getString(ALIAS_ADDRESS);
        double latitude = resultSet.getDouble(ALIAS_LATITUDE);
        double longitude = resultSet.getDouble(ALIAS_LONGITUDE);
        return new Venue(id, name, address, latitude, longitude);
    }
}
