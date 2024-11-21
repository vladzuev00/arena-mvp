package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Category;
import com.besmart.arena.crud.domain.Show;
import com.besmart.arena.crud.domain.Venue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getCategoryLazily;
import static com.besmart.arena.util.ResultSetUtil.getVenueLazily;

@Component
public final class ShowRowMapper implements RowMapper<Show> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_SHORT_ID = "external_short_id";
    static final String COLUMN_NAME_TITLE = "title";
    static final String COLUMN_NAME_SUBTITLE = "subtitle";
    static final String COLUMN_NAME_DESCRIPTION = "description";
    static final String COLUMN_NAME_CATEGORY_ID = "category_id";
    static final String COLUMN_NAME_VENUE_ID = "venue_id";
    static final String COLUMN_NAME_IMAGE_URL = "image_url";

    @Override
    public Show mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        int externalShortId = resultSet.getInt(COLUMN_NAME_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(COLUMN_NAME_TITLE);
        String subtitle = resultSet.getString(COLUMN_NAME_SUBTITLE);
        String description = resultSet.getString(COLUMN_NAME_DESCRIPTION);
        Category category = getCategoryLazily(resultSet, COLUMN_NAME_CATEGORY_ID);
        Venue venue = getVenueLazily(resultSet, COLUMN_NAME_VENUE_ID);
        String imageUrl = resultSet.getString(COLUMN_NAME_IMAGE_URL);
        return new Show(id, externalShortId, title, subtitle, description, category, venue, imageUrl);
    }
}
