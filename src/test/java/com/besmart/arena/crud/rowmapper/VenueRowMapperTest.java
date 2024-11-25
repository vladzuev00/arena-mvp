//package com.besmart.arena.crud.rowmapper;
//
//import com.besmart.arena.crud.domain.Venue;
//import com.besmart.arena.util.ResultSetUtil;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//
//import java.sql.ResultSet;
//import java.util.UUID;
//
//import static com.besmart.arena.crud.rowmapper.VenueRowMapper.*;
//import static com.besmart.arena.util.ResultSetUtil.getUUID;
//import static java.util.UUID.fromString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.*;
//
//public final class VenueRowMapperTest {
//    private final VenueRowMapper mapper = new VenueRowMapper();
//
//    @Test
//    public void rowShouldBeMapped()
//            throws Exception {
//        try (MockedStatic<ResultSetUtil> mockedUtil = mockStatic(ResultSetUtil.class)) {
//            ResultSet givenResultSet = mock(ResultSet.class);
//            int givenRowNumber = 20;
//
//            long givenId = 255;
//            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);
//
//            UUID givenExternalId = fromString("550e8400-e29b-41d4-a716-446655440000");
//            mockedUtil.when(() -> getUUID(same(givenResultSet), same(ALIAS_EXTERNAL_ID)))
//                    .thenReturn(givenExternalId);
//
//            String givenName = "test-name";
//            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);
//
//            String givenAddress = "test-address";
//            when(givenResultSet.getString(same(ALIAS_ADDRESS))).thenReturn(givenAddress);
//
//            double givenLatitude = 5.5;
//            when(givenResultSet.getDouble(same(ALIAS_LATITUDE))).thenReturn(givenLatitude);
//
//            double givenLongitude = 6.6;
//            when(givenResultSet.getDouble(same(ALIAS_LONGITUDE))).thenReturn(givenLongitude);
//
//            Venue actual = mapper.mapRow(givenResultSet, givenRowNumber);
//            var expected = new Venue(givenId, givenExternalId, givenName, givenAddress, givenLatitude, givenLongitude);
//            assertEquals(expected, actual);
//        }
//    }
//}
