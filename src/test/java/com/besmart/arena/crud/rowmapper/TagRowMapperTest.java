package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Tag;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.TagRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class TagRowMapperTest {
    private final TagRowMapper mapper = new TagRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        try (MockedStatic<ResultSetUtil> mockedUtil = mockStatic(ResultSetUtil.class)) {
            ResultSet givenResultSet = mock(ResultSet.class);
            int givenRowNumber = 20;

            long givenId = 255;
            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

            String givenName = "test-name";
            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

            Provider givenProvider = Provider.builder().id(256L).build();
            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
                    .thenReturn(givenProvider);

            Tag actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Tag expected = new Tag(givenId, givenName, givenProvider);
            assertEquals(expected, actual);
        }
    }
}
