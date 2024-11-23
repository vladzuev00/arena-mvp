//package com.besmart.arena.service.refresh;
//
//import com.besmart.arena.client.KakavaArenaClient;
//import com.besmart.arena.client.domain.*;
//import com.besmart.arena.crud.domain.Category;
//import com.besmart.arena.crud.domain.Promoter;
//import com.besmart.arena.crud.domain.Tag;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.UUID;
//
//import static com.besmart.arena.service.refresh.KakavaObjectRefresher.NOT_DEFINED_STRING;
//import static java.util.Collections.emptyList;
//import static java.util.UUID.fromString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public final class KakavaObjectRefresherTest {
//
//    @Mock
//    private KakavaArenaClient mockedClient;
//
//    private KakavaObjectRefresher refresher;
//
//    @BeforeEach
//    public void initializeRefresher() {
//        refresher = new KakavaObjectRefresher(null, null, null, null, null, null, null, mockedClient);
//    }
//
//    @Test
//    public void objectsShouldBeRequested() {
//        ShowsResponseTO givenResponse = mock(ShowsResponseTO.class);
//        when(mockedClient.requestShows()).thenReturn(givenResponse);
//
//        ShowsResponseTO actual = refresher.requestObjects();
//        assertSame(givenResponse, actual);
//    }
//
//    @Test
//    public void categorySourcesShouldBeGot() {
//        CategoryTO givenFirstSource = new CategoryTO(1);
//        CategoryTO givenSecondSource = new CategoryTO(2);
//        CategoryTO givenThirdSource = new CategoryTO(3);
//        CategoryTO givenFourthSource = new CategoryTO(4);
//        ShowsResponseTO givenResponse = new ShowsResponseTO(
//                List.of(
//                        ShowTO.builder()
//                                .eventCategories(List.of(givenFirstSource, givenSecondSource, givenThirdSource))
//                                .build(),
//                        ShowTO.builder()
//                                .eventCategories(List.of(givenFourthSource))
//                                .build(),
//                        ShowTO.builder()
//                                .eventCategories(emptyList())
//                                .build()
//                )
//        );
//
//        List<CategoryTO> actual = refresher.getCategorySources(givenResponse);
//        var expected = List.of(givenFirstSource, givenSecondSource, givenThirdSource, givenFourthSource);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void tagSourcesShouldBeGot() {
//        String givenFirstSource = "first-mark";
//        String givenSecondSource = "second-mark";
//        String givenThirdSource = "third-mark";
//        String givenFourthSource = "fourth-mark";
//        ShowsResponseTO givenResponse = new ShowsResponseTO(
//                List.of(
//                        ShowTO.builder()
//                                .marks(List.of(givenFirstSource, givenSecondSource, givenThirdSource))
//                                .build(),
//                        ShowTO.builder()
//                                .marks(List.of(givenFourthSource))
//                                .build(),
//                        ShowTO.builder()
//                                .marks(emptyList())
//                                .build()
//                )
//        );
//
//        List<String> actual = refresher.getTagSources(givenResponse);
//        List<String> expected = List.of(givenFirstSource, givenSecondSource, givenThirdSource, givenFourthSource);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void promoterSourcesShouldBeGot() {
//        PromoterTO firstGivenSource = mock(PromoterTO.class);
//        PromoterTO secondGivenSource = mock(PromoterTO.class);
//        PromoterTO thirdGivenSource = mock(PromoterTO.class);
//        ShowsResponseTO givenResponse = new ShowsResponseTO(
//                List.of(
//                        ShowTO.builder().promoter(firstGivenSource).build(),
//                        ShowTO.builder().promoter(secondGivenSource).build(),
//                        ShowTO.builder().promoter(thirdGivenSource).build()
//                )
//        );
//
//        List<PromoterTO> actual = refresher.getPromoterSources(givenResponse);
//        List<PromoterTO> expected = List.of(firstGivenSource, secondGivenSource, thirdGivenSource);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void venueSourcesShouldBeGot() {
//        List<ShowTO> givenShows = List.of(ShowTO.builder().build(), ShowTO.builder().build());
//        ShowsResponseTO givenResponse = new ShowsResponseTO(givenShows);
//
//        List<ShowTO> actual = refresher.getVenueSources(givenResponse);
//        assertSame(givenShows, actual);
//    }
//
//    @Test
//    public void showSourcesShouldBeGot() {
//        List<ShowTO> givenShows = List.of(ShowTO.builder().build(), ShowTO.builder().build());
//        ShowsResponseTO givenResponse = new ShowsResponseTO(givenShows);
//
//        List<ShowTO> actual = refresher.getShowSources(givenResponse);
//        assertSame(givenShows, actual);
//    }
//
//    @Test
//    public void categoryShouldBeCreated() {
//        int givenId = 2335;
//        CategoryTO givenSource = new CategoryTO(givenId);
//
//        Category actual = refresher.createCategory(givenSource);
//        Category expected = Category.builder()
//                .externalId(givenId)
//                .name(NOT_DEFINED_STRING)
//                .primaryColor(NOT_DEFINED_STRING)
//                .secondaryColor(NOT_DEFINED_STRING)
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void tagShouldBeCreated() {
//        String givenSource = "test-name";
//
//        Tag actual = refresher.createTag(givenSource);
//        Tag expected = Tag.builder().name(givenSource).build();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void promoterShouldBeCreated() {
//        UUID givenExternalId = fromString("a60de864-5c52-11ee-a81c-000d3aa868a2");
//        String givenName = "test-name";
//        PromoterTO givenSource = new PromoterTO(givenExternalId, givenName);
//
//        Promoter actual = refresher.createPromoter(givenSource);
//        Promoter expected = Promoter.builder()
//                .externalId(givenExternalId)
//                .name(givenName)
//                .iconUrl(NOT_DEFINED_STRING)
//                .webPageUrl(NOT_DEFINED_STRING)
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void venueShouldBeCreated() {
//        UUID givenExternalId = fromString("550e8400-e29b-41d4-a716-446655440001");
//        String givenName = "test-name";
//        String givenAddress = "test-address";
//        ShowTO givenSource = ShowTO.builder()
//                .location(ShowLocationTO.builder().id(givenExternalId).name(givenName).build())
//
//                .build();
//    }
//}
