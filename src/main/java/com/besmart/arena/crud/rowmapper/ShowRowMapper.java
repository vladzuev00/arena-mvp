package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.besmart.arena.util.ResultSetUtil.*;
import static java.util.Collections.singletonList;

@Component
public final class ShowRowMapper implements RowMapper<Show> {
    static final String ALIAS_ID = "showId";
    static final String ALIAS_EXTERNAL_ID = "showExternalId";
    static final String ALIAS_NAME = "showName";
    static final String ALIAS_DESCRIPTION = "showDescription";
    static final String ALIAS_START_DATE_TIME = "showStartDateTime";
    static final String ALIAS_END_DATE_TIME = "showEndDateTime";
    static final String ALIAS_YOUTUBE_URL = "showYouTubeUrl";
    static final String ALIAS_DISCOUNT = "showDiscount";
    static final String ALIAS_IMAGE_URL = "showImageUrl";
    static final String ALIAS_SALE_STATUS = "showSaleStatus";
    static final String ALIAS_DURATION = "showDuration";
    static final String ALIAS_TAGS = "showTags";
    static final String ALIAS_BROKEN = "showBroken";
    static final String ALIAS_PRICE_FROM = "showPriceFrom";
    static final String ALIAS_PRICE_FROM_WITH_TAXES = "showPriceFromWithTaxes";
    static final String ALIAS_CLUB_TICKETS_AVAILABLE = "showClubTicketsAvailable";
    static final String ALIAS_VENUE_ID = "showVenueId";
    static final String ALIAS_PROMOTER_ID = "showPromoterId";
    static final String ALIAS_PROVIDER_ID = "showProviderId";
    static final String ALIAS_CATEGORY_ID = "showCategoryId";

    @Override
    public Show mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String externalId = resultSet.getString(ALIAS_EXTERNAL_ID);
        String name = resultSet.getString(ALIAS_NAME);
        String description = resultSet.getString(ALIAS_DESCRIPTION);
        LocalDateTime startDateTime = getDateTime(resultSet, ALIAS_START_DATE_TIME);
        LocalDateTime endDateTime = getDateTime(resultSet, ALIAS_END_DATE_TIME);
        String youtubeUrl = resultSet.getString(ALIAS_YOUTUBE_URL);
        String discount = resultSet.getString(ALIAS_DISCOUNT);
        String imageUrl = resultSet.getString(ALIAS_IMAGE_URL);
        SaleStatus saleStatus = getEnum(resultSet, ALIAS_SALE_STATUS, SaleStatus.class);
        String duration = resultSet.getString(ALIAS_DURATION);
        String tags = resultSet.getString(ALIAS_TAGS);
        boolean broken = resultSet.getBoolean(ALIAS_BROKEN);
        int priceFrom = resultSet.getInt(ALIAS_PRICE_FROM);
        int priceFromWithTaxes = resultSet.getInt(ALIAS_PRICE_FROM_WITH_TAXES);
        boolean clubTicketsAvailable = resultSet.getBoolean(ALIAS_CLUB_TICKETS_AVAILABLE);
        Venue venue = getVenueLazily(resultSet, ALIAS_VENUE_ID);
        Promoter promoter = getPromoterLazily(resultSet, ALIAS_PROMOTER_ID);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        Category category = getCategoryLazily(resultSet, ALIAS_CATEGORY_ID);
        return new Show(
                id,
                externalId,
                name,
                description,
                startDateTime,
                endDateTime,
                youtubeUrl,
                discount,
                imageUrl,
                saleStatus,
                duration,
                tags,
                broken,
                priceFrom,
                priceFromWithTaxes,
                clubTicketsAvailable,
                venue,
                promoter,
                provider,
                singletonList(category)
        );
    }
}
