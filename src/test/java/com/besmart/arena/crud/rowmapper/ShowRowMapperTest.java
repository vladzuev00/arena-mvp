package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Category;
import com.besmart.arena.crud.dto.Show;
import com.besmart.arena.crud.dto.Venue;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.ShowRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getCategoryLazily;
import static com.besmart.arena.util.ResultSetUtil.getVenueLazily;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public final class ShowRowMapperTest {
    private final ShowRowMapper mapper = new ShowRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        try (MockedStatic<ResultSetUtil> mockedUtil = mockStatic(ResultSetUtil.class)) {
            ResultSet givenResultSet = mock(ResultSet.class);
            int givenRowNumber = 20;

            long givenId = 255;
            when(givenResultSet.getLong(same(COLUMN_NAME_ID))).thenReturn(givenId);

            int givenExternalShortId = 256;
            when(givenResultSet.getInt(same(COLUMN_NAME_EXTERNAL_SHORT_ID))).thenReturn(givenExternalShortId);

            String givenTitle = "test-title";
            when(givenResultSet.getString(same(COLUMN_NAME_TITLE))).thenReturn(givenTitle);

            String givenSubtitle = "test-subtitle";
            when(givenResultSet.getString(same(COLUMN_NAME_SUBTITLE))).thenReturn(givenSubtitle);

            String givenDescription = "test-description";
            when(givenResultSet.getString(same(COLUMN_NAME_DESCRIPTION))).thenReturn(givenDescription);

            Category givenCategory = Category.builder().id(257L).build();
            mockedUtil.when(() -> getCategoryLazily(same(givenResultSet), same(COLUMN_NAME_CATEGORY_ID)))
                    .thenReturn(givenCategory);

            Venue givenVenue = Venue.builder().id(258L).build();
            mockedUtil.when(() -> getVenueLazily(same(givenResultSet), same(COLUMN_NAME_VENUE_ID)))
                    .thenReturn(givenVenue);

            String givenImageUrl = "test-url";
            when(givenResultSet.getString(same(COLUMN_NAME_IMAGE_URL))).thenReturn(givenImageUrl);

            Show actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Show expected = new Show(
                    givenId,
                    givenExternalShortId,
                    givenTitle,
                    givenSubtitle,
                    givenDescription,
                    givenCategory,
                    givenVenue,
                    givenImageUrl
            );
            assertEquals(expected, actual);
        }
    }
}
