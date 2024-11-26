//package com.besmart.arena.crud.rowmapper;
//
//import com.besmart.arena.crud.domain.Promoter;
//import org.junit.jupiter.api.Test;
//
//import java.sql.ResultSet;
//
//import static com.besmart.arena.crud.rowmapper.PromoterRowMapper.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public final class PromoterRowMapperTest {
//    private final PromoterRowMapper mapper = new PromoterRowMapper();
//
//    @Test
//    public void rowShouldBeMapped()
//            throws Exception {
//        ResultSet givenResultSet = mock(ResultSet.class);
//        int givenRowNumber = 20;
//
//        long givenId = 255L;
//        when(givenResultSet.getLong(same(ALIAS_ID))).thenReturn(givenId);
//
//        String givenName = "test-name";
//        when(givenResultSet.getString(same(ALIAS_NAME))).thenReturn(givenName);
//
//        String givenIconUrl = "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2";
//        when(givenResultSet.getString(same(ALIAS_ICON_URL))).thenReturn(givenIconUrl);
//
//        String givenWebPageUrl = "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3";
//        when(givenResultSet.getString(same(ALIAS_WEB_PAGE_URL))).thenReturn(givenWebPageUrl);
//
//        Promoter actual = mapper.mapRow(givenResultSet, givenRowNumber);
//        Promoter expected = new Promoter(givenId, givenName, givenIconUrl, givenWebPageUrl);
//        assertEquals(expected, actual);
//    }
//}
