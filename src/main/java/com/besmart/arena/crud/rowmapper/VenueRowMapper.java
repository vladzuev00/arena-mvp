package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Venue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static com.besmart.arena.util.ResultSetUtil.getUUID;

@Component
public final class VenueRowMapper implements RowMapper<Venue> {
    static final String ALIAS_ID = "venueId";
    static final String ALIAS_EXTERNAL_ID = "venueExternalId";
    static final String ALIAS_NAME = "venueName";
    static final String ALIAS_ADDRESS = "venueAddress";
    static final String ALIAS_LATITUDE = "venueLatitude";
    static final String ALIAS_LONGITUDE = "venueLongitude";
    static final String ALIAS_PROVIDER_ID = "venueProviderId";

    @Override
    public Venue mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        UUID externalId = getUUID(resultSet, ALIAS_EXTERNAL_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String address = resultSet.getString(ALIAS_ADDRESS);
        double latitude = resultSet.getDouble(ALIAS_LATITUDE);
        double longitude = resultSet.getDouble(ALIAS_LONGITUDE);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return null;
//        return new Venue(id, externalId, name, address, latitude, longitude, provider);
    }
}
