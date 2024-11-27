//package com.besmart.arena.crud.rowmapper;
//
//import com.besmart.arena.crud.domain.*;
//import com.besmart.arena.util.ResultSetUtil;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//
//import java.sql.ResultSet;
//
//import static com.besmart.arena.crud.rowmapper.ShowRowMapper.*;
//import static com.besmart.arena.util.ResultSetUtil.*;
//import static java.util.Collections.singletonList;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public final class ShowRowMapperTest {
//
//    private final ShowRowMapper mapper = new ShowRowMapper();
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
//            String givenExternalShortId = "256";
//            when(givenResultSet.getString(same(ALIAS_EXTERNAL_SHORT_ID))).thenReturn(givenExternalShortId);
//
//            String givenTitle = "test-title";
//            when(givenResultSet.getString(same(ALIAS_TITLE))).thenReturn(givenTitle);
//
//            String givenSubtitle = "test-subtitle";
//            when(givenResultSet.getString(same(ALIAS_SUBTITLE))).thenReturn(givenSubtitle);
//
//            String givenDescription = "test-description";
//            when(givenResultSet.getString(same(ALIAS_DESCRIPTION))).thenReturn(givenDescription);
//
//            Venue givenVenue = Venue.builder().id(257L).build();
//            mockedUtil.when(() -> getVenueLazily(same(givenResultSet), same(ALIAS_VENUE_ID)))
//                    .thenReturn(givenVenue);
//
//            String givenImageUrl = "test-url";
//            when(givenResultSet.getString(same(ALIAS_IMAGE_URL))).thenReturn(givenImageUrl);
//
//            Promoter givenPromoter = Promoter.builder().id(258L).build();
//            mockedUtil.when(() -> getPromoterLazily(same(givenResultSet), same(ALIAS_PROMOTER_ID)))
//                    .thenReturn(givenPromoter);
//
//            Provider givenProvider = Provider.builder().id(261L).build();
//            mockedUtil.when(() -> getProviderLazily(same(givenResultSet), same(ALIAS_PROVIDER_ID)))
//                    .thenReturn(givenProvider);
//
//            Category givenCategory = Category.builder().id(259L).build();
//            mockedUtil.when(() -> getCategoryLazily(same(givenResultSet), same(ALIAS_CATEGORY_ID)))
//                    .thenReturn(givenCategory);
//
//            Tag givenTag = Tag.builder().id(260L).build();
//            mockedUtil.when(() -> getTagLazily(same(givenResultSet), same(ALIAS_TAG_ID))).thenReturn(givenTag);
//
//            Show actual = mapper.mapRow(givenResultSet, givenRowNumber);
//            Show expected = new Show(
//                    givenId,
//                    givenExternalShortId,
//                    givenTitle,
//                    givenSubtitle,
//                    givenDescription,
//                    givenVenue,
//                    givenImageUrl,
//                    givenPromoter,
//                    givenProvider,
//                    singletonList(givenCategory),
//                    singletonList(givenTag)
//            );
//            assertEquals(expected, actual);
//        }
//    }
//private Timestamp createTimestamp(LocalDateTime dateTime) {
//    Timestamp givenTimestamp = mock(Timestamp.class);
//    when(givenTimestamp.toLocalDateTime()).thenReturn(dateTime);
//    return givenTimestamp;
//}
//}
