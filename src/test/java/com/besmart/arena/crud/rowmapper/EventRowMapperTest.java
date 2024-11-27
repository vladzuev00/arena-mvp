package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Show;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.EventRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
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
            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

            String givenExternalId = "256";
            when(givenResultSet.getString(same(ALIAS_EXTERNAL_ID))).thenReturn(givenExternalId);

            Show givenShow = Show.builder().id(257L).build();
            mockedUtil.when(() -> getShowLazily(same(givenResultSet), same(ALIAS_SHOW_ID))).thenReturn(givenShow);

            Provider givenProvider = Provider.builder().id(258L).build();
            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
                    .thenReturn(givenProvider);

            Event actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Event expected = new Event(givenId, givenExternalId, givenShow, givenProvider);
            assertEquals(expected, actual);
        }
    }
}
