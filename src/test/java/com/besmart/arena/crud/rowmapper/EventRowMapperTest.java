package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Show;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.besmart.arena.crud.rowmapper.EventRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getShowLazily;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public final class EventRowMapperTest {
    private final EventRowMapper mapper = new EventRowMapper();

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

            LocalDateTime givenDateTime = LocalDateTime.of(2024, 11, 20, 21, 22, 23);
            Timestamp givenTimestamp = createTimestamp(givenDateTime);
            when(givenResultSet.getTimestamp(same(COLUMN_NAME_DATE_TIME))).thenReturn(givenTimestamp);

            Show givenShow = Show.builder().id(257L).build();
            mockedUtil.when(() -> getShowLazily(same(givenResultSet), same(COLUMN_NAME_SHOW_ID))).thenReturn(givenShow);

            Event actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Event expected = new Event(
                    givenId,
                    givenExternalShortId,
                    givenTitle,
                    givenSubtitle,
                    givenDescription,
                    givenDateTime,
                    givenShow
            );
            assertEquals(expected, actual);
        }
    }

    private Timestamp createTimestamp(LocalDateTime dateTime) {
        Timestamp givenTimestamp = mock(Timestamp.class);
        when(givenTimestamp.toLocalDateTime()).thenReturn(dateTime);
        return givenTimestamp;
    }
}
