package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getPromoterLazily;
import static com.besmart.arena.util.ResultSetUtil.getVenueLazily;
import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
public final class ShowRowMapper implements RowMapper<Show> {
    static final String ALIAS_ID = "showId";
    static final String ALIAS_EXTERNAL_SHORT_ID = "showExternalShortId";
    static final String ALIAS_TITLE = "showTitle";
    static final String ALIAS_SUBTITLE = "showSubtitle";
    static final String ALIAS_DESCRIPTION = "showDescription";
    static final String ALIAS_VENUE_ID = "showVenueId";
    static final String ALIAS_IMAGE_URL = "showImageUrl";
    static final String ALIAS_PROMOTER_ID = "showPromoterId";

    private final CategoryRowMapper categoryRowMapper;
    private final TagRowMapper tagRowMapper;

    @Override
    public Show mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        int externalShortId = resultSet.getInt(ALIAS_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(ALIAS_TITLE);
        String subtitle = resultSet.getString(ALIAS_SUBTITLE);
        String description = resultSet.getString(ALIAS_DESCRIPTION);
        Venue venue = getVenueLazily(resultSet, ALIAS_VENUE_ID);
        String imageUrl = resultSet.getString(ALIAS_IMAGE_URL);
        Promoter promoter = getPromoterLazily(resultSet, ALIAS_PROMOTER_ID);
        Category category = categoryRowMapper.mapRow(resultSet, rowNumber);
        Tag tag = tagRowMapper.mapRow(resultSet, rowNumber);
        return new Show(
                id,
                externalShortId,
                title,
                subtitle,
                description,
                venue,
                imageUrl,
                promoter,
                singletonList(category),
                singletonList(tag)
        );
    }
}
