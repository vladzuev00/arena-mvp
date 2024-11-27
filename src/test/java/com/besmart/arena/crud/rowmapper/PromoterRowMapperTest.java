package com.besmart.arena.crud.rowmapper;

import com.besmart.arena.crud.domain.Promoter;
import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.util.ResultSetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;

import static com.besmart.arena.crud.rowmapper.PromoterRowMapper.*;
import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);

            String givenExternalId = "256";
            when(givenResultSet.getString(same(ALIAS_EXTERNAL_ID))).thenReturn(givenExternalId);

            String givenName = "test-name";
            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);

            String givenAddress = "test-address";
            when(givenResultSet.getString(same(ALIAS_ADDRESS))).thenReturn(givenAddress);

            String givenEmail = "test-email";
            when(givenResultSet.getString(same(ALIAS_EMAIL))).thenReturn(givenEmail);

            String givenPhone = "test-phone";
            when(givenResultSet.getString(same(ALIAS_PHONE))).thenReturn(givenPhone);

            String givenImageUrl = "test-image-url";
            when(givenResultSet.getString(same(ALIAS_IMAGE_URL))).thenReturn(givenImageUrl);

            String givenExternalUrl = "test-external-url";
            when(givenResultSet.getString(same(ALIAS_EXTERNAL_URL))).thenReturn(givenExternalUrl);

            Provider givenProvider = Provider.builder().id(257L).build();
            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
                    .thenReturn(givenProvider);

            Promoter actual = mapper.mapRow(givenResultSet, givenRowNumber);
            Promoter expected = new Promoter(
                    givenId,
                    givenExternalId,
                    givenName,
                    givenAddress,
                    givenEmail,
                    givenPhone,
                    givenImageUrl,
                    givenExternalUrl,
                    givenProvider
            );
            assertEquals(expected, actual);
        }
    }
}
