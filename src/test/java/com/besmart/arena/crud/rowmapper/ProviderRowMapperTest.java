package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Provider;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.ProviderRowMapper.ALIAS_ID;
import static com.besmart.arena.crud.rowmapper.ProviderRowMapper.ALIAS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ProviderRowMapperTest {
    private final ProviderRowMapper mapper = new ProviderRowMapper();

    @Test
    public void rowShouldBeMapped()
            throws Exception {
        ResultSet givenResultSet = mock(ResultSet.class);
        int givenRowNumber = 20;

        long givenId = 255L;
        when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

        String givenName = "test-name";
        when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

        Provider actual = mapper.mapRow(givenResultSet, givenRowNumber);
        Provider expected = new Provider(givenId, givenName);
        assertEquals(expected, actual);
    }
}
