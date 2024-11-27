package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import static com.besmart.arena.crud.domain.SaleStatus.SELLING;
import static com.besmart.arena.crud.rowmapper.ShowRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.*;
import static java.util.Collections.singletonList;
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
            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

            String givenExternalId = "256";
            when(givenResultSet.getString(same(ALIAS_EXTERNAL_ID))).thenReturn(givenExternalId);

            String givenName = "test-name";
            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

            String givenDescription = "test-description";
            when(givenResultSet.getString(same(ALIAS_DESCRIPTION))).thenReturn(givenDescription);

            LocalDateTime givenStartDateTime = LocalDateTime.of(2024, 11, 27, 11, 4, 3);
            mockedUtil.when(() -> getDateTime(same(givenResultSet), same(ALIAS_START_DATE_TIME)))
                    .thenReturn(givenStartDateTime);

            LocalDateTime givenEndDateTime = LocalDateTime.of(2024, 11, 27, 11, 4, 5);
            mockedUtil.when(() -> getDateTime(same(givenResultSet), same(ALIAS_END_DATE_TIME)))
                    .thenReturn(givenEndDateTime);

            String givenYoutubeUrl = "test-youtube-url";
            when(givenResultSet.getString(same(ALIAS_YOUTUBE_URL))).thenReturn(givenYoutubeUrl);

            String givenDiscount = "test-discount";
            when(givenResultSet.getString(same(ALIAS_DISCOUNT))).thenReturn(givenDiscount);

            String givenImageUrl = "test-image-url";
            when(givenResultSet.getString(same(ALIAS_IMAGE_URL))).thenReturn(givenImageUrl);

            SaleStatus givenSaleStatus = SELLING;
            mockedUtil.when(() -> getEnum(same(givenResultSet), same(ALIAS_SALE_STATUS), same(SaleStatus.class)))
                    .thenReturn(givenSaleStatus);

            String givenDuration = "test-duration";
            when(givenResultSet.getString(same(ALIAS_DURATION))).thenReturn(givenDuration);

            String givenTags = "test-tags";
            when(givenResultSet.getString(same(ALIAS_TAGS))).thenReturn(givenTags);

            boolean givenBroken = true;
            when(givenResultSet.getBoolean(same(ALIAS_BROKEN))).thenReturn(givenBroken);

            int givenPriceFrom = 1000;
            when(givenResultSet.getInt(same(ALIAS_PRICE_FROM))).thenReturn(givenPriceFrom);

            int givenPriceFromWithTaxes = 900;
            when(givenResultSet.getInt(same(ALIAS_PRICE_FROM_WITH_TAXES))).thenReturn(givenPriceFromWithTaxes);

            boolean givenClubTicketsAvailable = false;
            when(givenResultSet.getBoolean(same(ALIAS_CLUB_TICKETS_AVAILABLE))).thenReturn(givenClubTicketsAvailable);

            Venue givenVenue = Venue.builder().id(257L).build();
            mockedUtil.when(() -> getVenueLazily(same(givenResultSet), same(ALIAS_VENUE_ID))).thenReturn(givenVenue);

            Promoter givenPromoter = Promoter.builder().id(258L).build();
            mockedUtil.when(() -> getPromoterLazily(same(givenResultSet), same(ALIAS_PROMOTER_ID)))
                    .thenReturn(givenPromoter);

            Provider givenProvider = Provider.builder().id(259L).build();
            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
                    .thenReturn(givenProvider);

            Category givenCategory = Category.builder().id(260L).build();
            mockedUtil.when(() -> getCategoryLazily(same(givenResultSet), same(ALIAS_CATEGORY_ID)))
                    .thenReturn(givenCategory);

            Show actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Show expected = new Show(
                    givenId,
                    givenExternalId,
                    givenName,
                    givenDescription,
                    givenStartDateTime,
                    givenEndDateTime,
                    givenYoutubeUrl,
                    givenDiscount,
                    givenImageUrl,
                    givenSaleStatus,
                    givenDuration,
                    givenTags,
                    givenBroken,
                    givenPriceFrom,
                    givenPriceFromWithTaxes,
                    givenClubTicketsAvailable,
                    givenVenue,
                    givenPromoter,
                    givenProvider,
                    singletonList(givenCategory)
            );
            assertEquals(expected, actual);
        }
    }
}
