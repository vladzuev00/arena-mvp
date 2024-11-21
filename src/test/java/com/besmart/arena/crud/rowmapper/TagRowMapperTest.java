package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Tag;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.TagRowMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TagRowMapperTest {
    private final TagRowMapper mapper = new TagRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        int givenRowNumber = 20;

        long givenId = 255;
        when(givenResultSet.getLong(same(COLUMN_NAME_ID))).thenReturn(givenId);

        int givenExternalId = 256;
        when(givenResultSet.getInt(same(COLUMN_NAME_EXTERNAL_ID))).thenReturn(givenExternalId);

        String givenName = "test-name";
        when(givenResultSet.getString(same(COLUMN_NAME_NAME))).thenReturn(givenName);

        Tag actual = mapper.mapRow(givenResultSet, givenRowNumber);
        Tag expected = new Tag(givenId, givenExternalId, givenName);
        assertEquals(expected, actual);
    }
}
