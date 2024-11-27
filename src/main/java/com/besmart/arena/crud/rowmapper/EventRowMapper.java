package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Show;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static com.besmart.arena.util.ResultSetUtil.getShowLazily;

@Component
public final class EventRowMapper implements RowMapper<Event> {
    static final String ALIAS_ID = "eventId";
    static final String ALIAS_EXTERNAL_ID = "eventExternalId";
    static final String ALIAS_SHOW_ID = "eventShowId";
    static final String ALIAS_PROVIDER_ID = "eventProviderId";

    @Override
    public Event mapRow(ResultSet resultSet, int rowNumber)
            throws SQLException {
        Long id = resultSet.getLong(ALIAS_ID);
        String externalId = resultSet.getString(ALIAS_EXTERNAL_ID);
        Show show = getShowLazily(resultSet, ALIAS_SHOW_ID);
        Provider provider = getProviderLazily(resultSet, ALIAS_PROVIDER_ID);
        return new Event(id, externalId, show, provider);
    }
}
