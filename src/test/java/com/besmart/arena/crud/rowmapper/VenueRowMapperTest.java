package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Venue;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.VenueRowMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class VenueRowMapperTest {
    private final VenueRowMapper mapper = new VenueRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        int givenRowNumber = 20;

        long givenId = 255;
        when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

        String givenName = "test-name";
        when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

        String givenAddress = "test-address";
        when(givenResultSet.getString(same(ALIAS_ADDRESS))).thenReturn(givenAddress);

        double givenLatitude = 5.5;
        when(givenResultSet.getDouble(same(ALIAS_LATITUDE))).thenReturn(givenLatitude);

        double givenLongitude = 6.6;
        when(givenResultSet.getDouble(same(ALIAS_LONGITUDE))).thenReturn(givenLongitude);

        Venue actual = mapper.mapRow(givenResultSet, givenRowNumber);
        Venue expected = new Venue(givenId, givenName, givenAddress, givenLatitude, givenLongitude);
        assertEquals(expected, actual);
    }
}
