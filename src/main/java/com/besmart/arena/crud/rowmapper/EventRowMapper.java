package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Event;
import com.besmart.arena.crud.dto.Show;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.besmart.arena.util.ResultSetUtil.getShowLazily;

@Component
public final class EventRowMapper implements RowMapper<Event> {
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_EXTERNAL_SHORT_ID = "external_short_id";
    static final String COLUMN_NAME_TITLE = "title";
    static final String COLUMN_NAME_SUBTITLE = "subtitle";
    static final String COLUMN_NAME_DESCRIPTION = "description";
    static final String COLUMN_NAME_DATE_TIME = "date_time";
    static final String COLUMN_NAME_SHOW_ID = "show_id";

    @Override
    public Event mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(COLUMN_NAME_ID);
        int externalShortId = resultSet.getInt(COLUMN_NAME_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(COLUMN_NAME_TITLE);
        String subtitle = resultSet.getString(COLUMN_NAME_SUBTITLE);
        String description = resultSet.getString(COLUMN_NAME_DESCRIPTION);
        LocalDateTime dateTime = resultSet.getTimestamp(COLUMN_NAME_DATE_TIME).toLocalDateTime();
        Show show = getShowLazily(resultSet, COLUMN_NAME_SHOW_ID);
        return new Event(id, externalShortId, title, subtitle, description, dateTime, show);
    }
}
