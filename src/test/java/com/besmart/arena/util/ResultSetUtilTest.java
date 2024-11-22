package com.besmart.arena.util;

import com.besmart.arena.crud.domain.Category;
import com.besmart.arena.crud.domain.Show;
import com.besmart.arena.crud.domain.Venue;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.besmart.arena.util.ResultSetUtil.*;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ResultSetUtilTest {

    @Test
    public void uuidShouldBeGot()
            throws SQLException {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenColumnName = "id";

        String givenUUIDString = "550e8400-e29b-41d4-a716-446655440000";
        when(givenResultSet.getString(same(givenColumnName))).thenReturn(givenUUIDString);

        UUID actual = getUUID(givenResultSet, givenColumnName);
        UUID expected = fromString(givenUUIDString);
        assertEquals(expected, actual);
    }

    @Test
    public void categoryShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenColumnNameId = "id";

        final long givenId = 255;
        when(givenResultSet.getLong(same(givenColumnNameId))).thenReturn(givenId);

        Category actual = getCategoryLazily(givenResultSet, givenColumnNameId);
        Category expected = Category.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void venueShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenColumnNameId = "id";

        final long givenId = 255;
        when(givenResultSet.getLong(same(givenColumnNameId))).thenReturn(givenId);

        Venue actual = getVenueLazily(givenResultSet, givenColumnNameId);
        Venue expected = Venue.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void showShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenColumnNameId = "id";

        final long givenId = 255;
        when(givenResultSet.getLong(same(givenColumnNameId))).thenReturn(givenId);

        Show actual = getShowLazily(givenResultSet, givenColumnNameId);
        Show expected = Show.builder().id(givenId).build();
        assertEquals(expected, actual);
    }
}