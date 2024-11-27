package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Category;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.CategoryRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

public final class CategoryRowMapperTest {
    private final CategoryRowMapper mapper = new CategoryRowMapper();

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

            String givenName = "test-name";
            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

            boolean givenHidden = true;
            when(givenResultSet.getBoolean(same(ALIAS_HIDDEN))).thenReturn(givenHidden);

            Provider givenProvider = Provider.builder().id(257L).build();
            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
                    .thenReturn(givenProvider);

            Category actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Category expected = new Category(givenId, givenExternalId, givenName, givenHidden, givenProvider);
            assertEquals(expected, actual);
        }
    }
}
