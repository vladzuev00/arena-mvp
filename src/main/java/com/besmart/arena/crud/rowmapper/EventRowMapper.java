package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Show;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
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
    static final String ALIAS_PROVIDER_ID = "eventProviderId";

    @Override
    public Event mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String externalShortId = resultSet.getString(ALIAS_EXTERNAL_SHORT_ID);
        String title = resultSet.getString(ALIAS_TITLE);
        String subtitle = resultSet.getString(ALIAS_SUBTITLE);
        String description = resultSet.getString(ALIAS_DESCRIPTION);
        LocalDateTime dateTime = resultSet.getTimestamp(ALIAS_DATE_TIME).toLocalDateTime();
        Show show = getShowLazily(resultSet, ALIAS_SHOW_ID);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return null;
//        return new Event(id, externalShortId, title, subtitle, description, dateTime, show, provider);
    }
}
