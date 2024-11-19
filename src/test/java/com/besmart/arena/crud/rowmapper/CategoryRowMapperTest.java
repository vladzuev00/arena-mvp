package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Category;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.CategoryRowMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class CategoryRowMapperTest {
    private final CategoryRowMapper mapper = new CategoryRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        int givenRowNumber = 20;

        long givenId = 255;
        when(givenResultSet.getLong(same(COLUMN_NAME_ID))).thenReturn(givenId);

        int givenInternalId = 55;
        when(givenResultSet.getInt(same(COLUMN_NAME_EXTERNAL_ID))).thenReturn(givenInternalId);

        String givenName = "test-name";
        when(givenResultSet.getString(same(COLUMN_NAME_NAME))).thenReturn(givenName);

        String givenPrimaryColor = "#3d4761";
        when(givenResultSet.getString(same(COLUMN_NAME_PRIMARY_COLOR))).thenReturn(givenPrimaryColor);

        String givenSecondaryColor = "#3d4762";
        when(givenResultSet.getString(same(COLUMN_NAME_SECONDARY_COLOR))).thenReturn(givenSecondaryColor);

        Category actual = mapper.mapRow(givenResultSet, givenRowNumber);
        Category expected = new Category(givenId, givenInternalId, givenName, givenPrimaryColor, givenSecondaryColor);
        assertEquals(expected, actual);
    }
}
