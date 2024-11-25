//package com.besmart.arena.crud.rowmapper;
//
//import com.besmart.arena.crud.domain.Category;
//import com.besmart.arena.crud.domain.Provider;
//import com.besmart.arena.util.ResultSetUtil;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//
//import java.sql.ResultSet;
//
//import static com.besmart.arena.crud.rowmapper.CategoryRowMapper.*;
//import static com.besmart.arena.util.ResultSetUtil.getProviderLazily;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.*;
//
//public final class CategoryRowMapperTest {
//    private final CategoryRowMapper mapper = new CategoryRowMapper();
//
//    @Test
//    public void rowShouldBeMapped()
//            throws Exception {
//        try (MockedStatic<ResultSetUtil> mockedUtil = mockStatic(ResultSetUtil.class)) {
//            ResultSet givenResultSet = mock(ResultSet.class);
//            int givenRowNumber = 20;
//
//            long givenId = 255;
//            when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);
//
//            int givenInternalId = 55;
//            when(givenResultSet.getInt(same(ALIAS_EXTERNAL_ID))).thenReturn(givenInternalId);
//
//            String givenName = "test-name";
//            when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);
//
//            String givenPrimaryColor = "#3d4761";
//            when(givenResultSet.getString(same(ALIAS_PRIMARY_COLOR))).thenReturn(givenPrimaryColor);
//
//            String givenSecondaryColor = "#3d4762";
//            when(givenResultSet.getString(same(ALIAS_SECONDARY_COLOR))).thenReturn(givenSecondaryColor);
//
//            Provider givenProvider = Provider.builder().id(256L).build();
//            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
//                    .thenReturn(givenProvider);
//
//            Category actual = mapper.mapRow(givenResultSet, givenRowNumber);
//            Category expected = new Category(
//                    givenId,
//                    givenInternalId,
//                    givenName,
//                    givenPrimaryColor,
//                    givenSecondaryColor,
//                    givenProvider
//            );
//            assertEquals(expected, actual);
//        }
//    }
//}
