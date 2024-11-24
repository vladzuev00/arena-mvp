package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.ShowRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getPromoterLazily;
import static com.besmart.arena.util.ResultSetUtil.getVenueLazily;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class ShowRowMapperTest {

    @Mock
    private CategoryRowMapper mockedCategoryRowMapper;

    @Mock
    private TagRowMapper mockedTagRowMapper;

    private ShowRowMapper showRowMapper;

    @BeforeEach
    public void initializeShowRowMapper() {
        showRowMapper = new ShowRowMapper(mockedCategoryRowMapper, mockedTagRowMapper);
    }

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

            Venue givenVenue = Venue.builder().id(257L).build();
            mockedUtil.when(() -> getVenueLazily(same(givenResultSet), same(COLUMN_NAME_VENUE_ID)))
                    .thenReturn(givenVenue);

            String givenImageUrl = "test-url";
            when(givenResultSet.getString(same(COLUMN_NAME_IMAGE_URL))).thenReturn(givenImageUrl);

            Promoter givenPromoter = Promoter.builder().id(258L).build();
            mockedUtil.when(() -> getPromoterLazily(same(givenResultSet), same(COLUMN_NAME_PROMOTER_ID)))
                    .thenReturn(givenPromoter);

            Category givenCategory = Category.builder().id(259L).build();
            when(mockedCategoryRowMapper.mapRow(same(givenResultSet), eq(givenRowNumber))).thenReturn(givenCategory);

            Tag givenTag = Tag.builder().id(260L).build();
            when(mockedTagRowMapper.mapRow(same(givenResultSet), eq(givenRowNumber))).thenReturn(givenTag);

            Show actual = showRowMapper.mapRow(givenResultSet, givenRowNumber);
            Show expected = new Show(
                    givenId,
                    givenExternalShortId,
                    givenTitle,
                    givenSubtitle,
                    givenDescription,
                    givenVenue,
                    givenImageUrl,
                    givenPromoter,
                    singletonList(givenCategory),
                    singletonList(givenTag)
            );
            assertEquals(expected, actual);
        }
    }
}
