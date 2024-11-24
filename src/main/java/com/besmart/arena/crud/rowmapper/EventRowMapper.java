package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Show;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.besmart.arena.util.ResultSetUtil.getShowLazily;

@Component
public final class EventRowMapper implements RowMapper<Event> {
    static final String ALIAS_ID = "eventId";
    static final String ALIAS_EXTERNAL_SHORT_ID = "eventExternalShortId";
    static final String ALIAS_TITLE = "eventTitle";
    static final String ALIAS_SUBTITLE = "eventSubtitle";
    static final String ALIAS_DESCRIPTION = "eventDescription";
    static final String ALIAS_DATE_TIME = "eventDateTime";
    static final String ALIAS_SHOW_ID = "eventShowId";

    @Override
    public Event mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        int externalShortId = resultSet.getInt(ALIAS_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(ALIAS_TITLE);
        String subtitle = resultSet.getString(ALIAS_SUBTITLE);
        String description = resultSet.getString(ALIAS_DESCRIPTION);
        LocalDateTime dateTime = resultSet.getTimestamp(ALIAS_DATE_TIME).toLocalDateTime();
        Show show = getShowLazily(resultSet, ALIAS_SHOW_ID);
        return new Event(id, externalShortId, title, subtitle, description, dateTime, show);
    }
}
