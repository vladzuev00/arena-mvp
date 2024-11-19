package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.dto.Promoter;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;
import java.util.UUID;

import static com.besmart.arena.crud.rowmapper.PromoterRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getUUID;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class PromoterRowMapperTest {
    private final PromoterRowMapper mapper = new PromoterRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        try (MockedStatic<ResultSetUtil> mockedUtil = mockStatic(ResultSetUtil.class)) {
            ResultSet givenResultSet = mock(ResultSet.class);
            int givenRowNumber = 20;

            long givenId = 255L;
            when(givenResultSet.getLong(same(COLUMN_NAME_ID))).thenReturn(givenId);

            UUID givenExternalId = fromString("a60de864-5c52-11ee-a81c-000d3aa868a2");
            mockedUtil.when(() -> getUUID(same(givenResultSet), eq(COLUMN_NAME_EXTERNAL_ID)))
                    .thenReturn(givenExternalId);

            String givenName = "test-name";
            when(givenResultSet.getString(same(COLUMN_NAME_NAME))).thenReturn(givenName);

            String givenIconUrl = "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2";
            when(givenResultSet.getString(same(COLUMN_NAME_ICON_URL))).thenReturn(givenIconUrl);

            String givenWebPageUrl = "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3";
            when(givenResultSet.getString(same(COLUMN_NAME_WEB_PAGE_URL))).thenReturn(givenWebPageUrl);

            Promoter actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Promoter expected = new Promoter(givenId, givenExternalId, givenName, givenIconUrl, givenWebPageUrl);
            assertEquals(expected, actual);
        }
    }
}
