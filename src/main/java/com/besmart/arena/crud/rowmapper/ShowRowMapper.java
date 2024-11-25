package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.*;
import static java.util.Collections.singletonList;

@Component
public final class ShowRowMapper implements RowMapper<Show> {
    static final String ALIAS_ID = "showId";
    static final String ALIAS_EXTERNAL_SHORT_ID = "showExternalShortId";
    static final String ALIAS_TITLE = "showTitle";
    static final String ALIAS_SUBTITLE = "showSubtitle";
    static final String ALIAS_DESCRIPTION = "showDescription";
    static final String ALIAS_VENUE_ID = "showVenueId";
    static final String ALIAS_IMAGE_URL = "showImageUrl";
    static final String ALIAS_PROMOTER_ID = "showPromoterId";
    static final String ALIAS_CATEGORY_ID = "showCategoryId";
    static final String ALIAS_TAG_ID = "showTagId";
    static final String ALIAS_PROVIDER_ID = "showProviderId";

    @Override
    public Show mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String externalShortId = resultSet.getString(ALIAS_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(ALIAS_TITLE);
        String subtitle = resultSet.getString(ALIAS_SUBTITLE);
        String description = resultSet.getString(ALIAS_DESCRIPTION);
        Venue venue = getVenueLazily(resultSet, ALIAS_VENUE_ID);
        String imageUrl = resultSet.getString(ALIAS_IMAGE_URL);
        Promoter promoter = getPromoterLazily(resultSet, ALIAS_PROMOTER_ID);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        Category category = getCategoryLazily(resultSet, ALIAS_CATEGORY_ID);
        Tag tag = getTagLazily(resultSet, ALIAS_TAG_ID);
        return new Show(
                id,
                externalShortId,
                title,
                subtitle,
                description,
                venue,
                imageUrl,
                promoter,
                provider,
                singletonList(category),
                singletonList(tag)
        );
    }
}
