package com.besmart.arena.util;

import com.besmart.arena.crud.domain.*;
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
        String givenAlias = "id";

        String givenUUIDString = "550e8400-e29b-41d4-a716-446655440000";
        when(givenResultSet.getString(same(givenAlias))).thenReturn(givenUUIDString);

        UUID actual = getUUID(givenResultSet, givenAlias);
        UUID expected = fromString(givenUUIDString);
        assertEquals(expected, actual);
    }

    @Test
    public void venueShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenAliasId = "id";

        long givenId = 255;
        when(givenResultSet.getLong(same(givenAliasId))).thenReturn(givenId);

        Venue actual = getVenueLazily(givenResultSet, givenAliasId);
        Venue expected = Venue.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void promoterShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenAliasId = "id";

        long givenId = 255;
        when(givenResultSet.getLong(same(givenAliasId))).thenReturn(givenId);

        Promoter actual = getPromoterLazily(givenResultSet, givenAliasId);
        Promoter expected = Promoter.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void categoryShouldBeGotLazily()
            throws SQLException {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenAliasId = "id";

        long givenId = 255;
        when(givenResultSet.getLong(same(givenAliasId))).thenReturn(givenId);

        Category actual = getCategoryLazily(givenResultSet, givenAliasId);
        Category expected = Category.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void tagShouldBeGotLazily()
            throws SQLException {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenAliasId = "id";

        long givenId = 255;
        when(givenResultSet.getLong(same(givenAliasId))).thenReturn(givenId);

        Tag actual = getTagLazily(givenResultSet, givenAliasId);
        Tag expected = Tag.builder().id(givenId).build();
        assertEquals(expected, actual);
    }

    @Test
    public void showShouldBeGotLazily()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        String givenAliasId = "id";

        long givenId = 255;
        when(givenResultSet.getLong(same(givenAliasId))).thenReturn(givenId);

        Show actual = getShowLazily(givenResultSet, givenAliasId);
        Show expected = Show.builder().id(givenId).build();
        assertEquals(expected, actual);
    }
}
