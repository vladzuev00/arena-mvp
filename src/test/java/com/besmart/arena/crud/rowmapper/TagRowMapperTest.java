package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Tag;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.TagRowMapper.COLUMN_NAME_ID;
import static com.besmart.arena.crud.rowmapper.TagRowMapper.COLUMN_NAME_NAME;
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

        String givenName = "test-name";
        when(givenResultSet.getString(same(COLUMN_NAME_NAME))).thenReturn(givenName);

        Tag actual = mapper.mapRow(givenResultSet, givenRowNumber);
        Tag expected = new Tag(givenId, givenName);
        assertEquals(expected, actual);
    }
}
