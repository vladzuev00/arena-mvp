package com.besmart.arena.service.refresh;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import com.besmart.arena.service.cache.ProviderCache;
import com.besmart.arena.service.refresh.base.ArenaObjectRefresher;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class ArenaObjectRefresherTest {

    @Mock
    private ProviderCache mockedProviderCache;

    @Mock
    private TransactionTemplate mockedTransactionTemplate;

    @Mock
    private CategoryRepository mockedCategoryRepository;

    @Mock
    private TagRepository mockedTagRepository;

    @Mock
    private PromoterRepository mockedPromoterRepository;

    @Mock
    private VenueRepository mockedVenueRepository;

    @Mock
    private ShowRepository mockedShowRepository;

    @Mock
    private EventRepository mockedEventRepository;

    @Mock
    private TestClient mockedClient;

    @Captor
    private ArgumentCaptor<Consumer<TransactionStatus>> transactionAction;

    @Captor
    private ArgumentCaptor<List<Category>> categoriesArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Tag>> tagsArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Promoter>> promotersArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Venue>> venuesArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Show>> showsArgumentCaptor;

    @Captor
    private ArgumentCaptor<List<Event>> eventsArgumentCaptor;

    private TestArenaObjectRefresher refresher;

    @BeforeEach
    public void initializeRefresher() {
        refresher = new TestArenaObjectRefresher(
                mockedProviderCache,
                mockedTransactionTemplate,
                mockedCategoryRepository,
                mockedTagRepository,
                mockedPromoterRepository,
                mockedVenueRepository,
                mockedShowRepository,
                mockedEventRepository,
                mockedClient
        );
    }

    @Test
    public void objectsShouldBeRefreshed() {
        TestResponse givenResponse = new TestResponse(
                List.of(
                        new TestEventSource(
                                "2334",
                                "first-event-title",
                                "first-event-subtitle",
                                "first-event-description",
                                LocalDateTime.of(2024, 11, 24, 9, 11, 12),
                                new TestShowSource(
                                        "2335",
                                        "first-show-title",
                                        "first-show-subtitle",
                                        "first-show-description",
                                        new TestVenueSource("first-venue-name", "first-venue-address", 5.5, 6.6),
                                        "first-show-image-url",
                                        new TestPromoterSource(
                                                "first-promoter-name",
                                                "first-promoter-icon-url",
                                                "first-promoter-web-page-url"
                                        ),
                                        List.of(
                                                new TestCategorySource(
                                                        "first-category-name",
                                                        "first-category-primary-color",
                                                        "first-category-secondary-color"
                                                ),
                                                new TestCategorySource(
                                                        "second-category-name",
                                                        "second-category-primary-color",
                                                        "second-category-secondary-color"
                                                )
                                        ),
                                        List.of(new TestTagSource("first-tag"), new TestTagSource("second-tag"))
                                )
                        ),
                        new TestEventSource(
                                "2338",
                                "second-event-title",
                                "second-event-subtitle",
                                "second-event-description",
                                LocalDateTime.of(2024, 11, 24, 9, 11, 13),
                                new TestShowSource(
                                        "2339",
                                        "second-show-title",
                                        "second-show-subtitle",
                                        "second-show-description",
                                        new TestVenueSource("second-venue-name", "second-venue-address", 7.7, 8.8),
                                        "second-show-image-url",
                                        new TestPromoterSource(
                                                "second-promoter-name",
                                                "second-promoter-icon-url",
                                                "second-promoter-web-page-url"
                                        ),
                                        List.of(
                                                new TestCategorySource(
                                                        "third-category-name",
                                                        "third-category-primary-color",
                                                        "third-category-secondary-color"
                                                ),
                                                new TestCategorySource(
                                                        "fourth-category-name",
                                                        "fourth-category-primary-color",
                                                        "fourth-category-secondary-color"
                                                )
                                        ),
                                        List.of(new TestTagSource("third-tag"), new TestTagSource("fourth-tag"))
                                )
                        )
                )
        );
        when(mockedClient.request()).thenReturn(givenResponse);

        refresher.refresh();

        Provider givenProvider = Provider.builder().id(255L).build();
        when(mockedProviderCache.get(same(TestArenaObjectRefresher.PROVIDER_NAME)))
                .thenReturn(Optional.of(givenProvider));

        verify(mockedTransactionTemplate, times(1)).executeWithoutResult(transactionAction.capture());
        transactionAction.getValue().accept(null);

        verify(mockedCategoryRepository, times(1)).refreshByExternalId(categoriesArgumentCaptor.capture());
        List<Category> actualCategories = categoriesArgumentCaptor.getValue();
        List<Category> expectedCategories = List.of(
                Category.builder()
                        .name("first-category-name")
                        .primaryColor("first-category-primary-color")
                        .secondaryColor("first-category-secondary-color")
                        .build(),
                Category.builder()
                        .name("second-category-name")
                        .primaryColor("second-category-primary-color")
                        .secondaryColor("second-category-secondary-color")
                        .build(),
                Category.builder()
                        .name("third-category-name")
                        .primaryColor("third-category-primary-color")
                        .secondaryColor("third-category-secondary-color")
                        .build(),
                Category.builder()
                        .name("fourth-category-name")
                        .primaryColor("fourth-category-primary-color")
                        .secondaryColor("fourth-category-secondary-color")
                        .build()
        );
        assertEquals(expectedCategories, actualCategories);

        verify(mockedTagRepository, times(1)).refreshByName(tagsArgumentCaptor.capture());
        List<Tag> actualTags = tagsArgumentCaptor.getValue();
        List<Tag> expectedTags = List.of(
                Tag.builder().name("first-tag").build(),
                Tag.builder().name("second-tag").build(),
                Tag.builder().name("third-tag").build(),
                Tag.builder().name("fourth-tag").build()
        );
        assertEquals(expectedTags, actualTags);

        verify(mockedPromoterRepository, times(1)).refreshByExternalId(promotersArgumentCaptor.capture());
        List<Promoter> actualPromoters = promotersArgumentCaptor.getValue();
        List<Promoter> expectedPromoters = List.of(
                Promoter.builder()
                        .name("first-promoter-name")
                        .imageUrl("first-promoter-icon-url")
                        .externalUrl("first-promoter-web-page-url")
                        .build(),
                Promoter.builder()
                        .name("second-promoter-name")
                        .imageUrl("second-promoter-icon-url")
                        .externalUrl("second-promoter-web-page-url")
                        .build()
        );
        assertEquals(expectedPromoters, actualPromoters);

        verify(mockedVenueRepository, times(1)).refreshByName(venuesArgumentCaptor.capture());
        List<Venue> actualVenues = venuesArgumentCaptor.getValue();
        List<Venue> expectedVenues = List.of(
                Venue.builder()
                        .name("first-venue-name")
                        .address("first-venue-address")
                        .latitude(5.5)
                        .longitude(6.6)
                        .build(),
                Venue.builder()
                        .name("second-venue-name")
                        .address("second-venue-address")
                        .latitude(7.7)
                        .longitude(8.8)
                        .build()
        );
        assertEquals(expectedVenues, actualVenues);

        verify(mockedShowRepository, times(1)).refreshByExternalId(showsArgumentCaptor.capture());
        List<Show> actualShows = showsArgumentCaptor.getValue();
        List<Show> expectedShows = List.of(
                Show.builder()
                        .externalId("2335")
                        .title("first-show-title")
                        .subtitle("first-show-subtitle")
                        .description("first-show-description")
                        .imageUrl("first-show-image-url")
                        .provider(givenProvider)
                        .build(),
                Show.builder()
                        .externalId("2339")
                        .title("second-show-title")
                        .subtitle("second-show-subtitle")
                        .description("second-show-description")
                        .imageUrl("second-show-image-url")
                        .provider(givenProvider)
                        .build()
        );
        assertEquals(expectedShows, actualShows);

        verify(mockedEventRepository, times(1)).refreshByExternalId(eventsArgumentCaptor.capture());
        List<Event> actualEvents = eventsArgumentCaptor.getValue();
        List<Event> expectedEvents = List.of(
                Event.builder()
                        .externalId("2334")
                        .title("first-event-title")
                        .subtitle("first-event-subtitle")
                        .description("first-event-description")
                        .dateTime(LocalDateTime.of(2024, 11, 24, 9, 11, 12))
                        .provider(givenProvider)
                        .build(),
                Event.builder()
                        .externalId("2338")
                        .title("second-event-title")
                        .subtitle("second-event-subtitle")
                        .description("second-event-description")
                        .dateTime(LocalDateTime.of(2024, 11, 24, 9, 11, 13))
                        .provider(givenProvider)
                        .build()
        );
        assertEquals(expectedEvents, actualEvents);
    }

    @Test
    public void objectsShouldBeRefreshedBecauseOfNoSuchProvider() {
        TestResponse givenResponse = new TestResponse(emptyList());
        when(mockedClient.request()).thenReturn(givenResponse);

        refresher.refresh();

        when(mockedProviderCache.get(same(TestArenaObjectRefresher.PROVIDER_NAME))).thenReturn(empty());

        verify(mockedTransactionTemplate, times(1)).executeWithoutResult(transactionAction.capture());
        assertThrows(IllegalStateException.class, () -> transactionAction.getValue().accept(null));

        verifyNoInteractions(
                mockedCategoryRepository,
                mockedTagRepository,
                mockedPromoterRepository,
                mockedVenueRepository,
                mockedShowRepository,
                mockedEventRepository
        );
    }

    @Value
    private static class TestCategorySource {
        String name;
        String primaryColor;
        String secondaryColor;
    }

    @Value
    private static class TestTagSource {
        String name;
    }

    @Value
    private static class TestPromoterSource {
        String name;
        String iconUrl;
        String webPageUrl;
    }

    @Value
    private static class TestVenueSource {
        String name;
        String address;
        double latitude;
        double longitude;
    }

    @Value
    private static class TestShowSource {
        String externalShortId;
        String title;
        String subtitle;
        String description;
        TestVenueSource venue;
        String imageUrl;
        TestPromoterSource promoter;
        List<TestCategorySource> categories;
        List<TestTagSource> tags;
    }

    @Value
    private static class TestEventSource {
        String externalShortId;
        String title;
        String subtitle;
        String description;
        LocalDateTime dateTime;
        TestShowSource show;
    }

    @Value
    private static class TestResponse {
        List<TestEventSource> events;
    }

    private interface TestClient {
        TestResponse request();
    }

    private static final class TestArenaObjectRefresher extends ArenaObjectRefresher<TestResponse, TestCategorySource, TestTagSource, TestPromoterSource, TestVenueSource, TestShowSource, TestEventSource> {
        private static final String PROVIDER_NAME = "TEST-PROVIDER";

        private final TestClient client;

        public TestArenaObjectRefresher(ProviderCache providerCache,
                                        TransactionTemplate transactionTemplate,
                                        CategoryRepository categoryRepository,
                                        TagRepository tagRepository,
                                        PromoterRepository promoterRepository,
                                        VenueRepository venueRepository,
                                        ShowRepository showRepository,
                                        EventRepository eventRepository,
                                        TestClient client) {
            super(
                    providerCache,
                    PROVIDER_NAME,
                    transactionTemplate,
                    categoryRepository,
                    tagRepository,
                    promoterRepository,
                    venueRepository,
                    showRepository,
                    eventRepository
            );
            this.client = client;
        }

        @Override
        protected TestResponse requestObjects() {
            return client.request();
        }

        @Override
        protected List<TestCategorySource> getCategoryTos(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .flatMap(event -> event.getShow().getCategories().stream())
                    .toList();
        }

        @Override
        protected List<TestTagSource> getTagSources(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .flatMap(event -> event.getShow().getTags().stream())
                    .toList();
        }

        @Override
        protected List<TestPromoterSource> getPromoterTos(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(event -> event.getShow().getPromoter())
                    .toList();
        }

        @Override
        protected List<TestVenueSource> getVenueTos(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(event -> event.getShow().getVenue())
                    .toList();
        }

        @Override
        protected List<TestShowSource> getShowTos(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(TestEventSource::getShow)
                    .toList();
        }

        @Override
        protected List<TestEventSource> getEventTos(TestResponse response) {
            return response.getEvents();
        }

        @Override
        protected Category createCategory(TestCategorySource source) {
            return Category.builder()
                    .name(source.getName())
                    .primaryColor(source.getPrimaryColor())
                    .secondaryColor(source.getSecondaryColor())
                    .build();
        }

        @Override
        protected Tag createTag(TestTagSource source) {
            return Tag.builder().name(source.getName()).build();
        }

        @Override
        protected Promoter createPromoter(TestPromoterSource source) {
            return Promoter.builder()
                    .name(source.getName())
                    .imageUrl(source.getIconUrl())
                    .externalUrl(source.getWebPageUrl())
                    .build();
        }

        @Override
        protected Venue createVenue(TestVenueSource source) {
            return Venue.builder()
                    .name(source.getName())
                    .address(source.getAddress())
                    .latitude(source.getLatitude())
                    .longitude(source.getLongitude())
                    .build();
        }

        @Override
        protected Show createShow(TestShowSource source, Provider provider) {
            return Show.builder()
                    .externalId(source.getExternalShortId())
                    .title(source.getTitle())
                    .subtitle(source.getSubtitle())
                    .description(source.getDescription())
                    .imageUrl(source.getImageUrl())
                    .provider(provider)
                    .build();
        }

        @Override
        protected Event createEvent(TestEventSource source, Provider provider) {
            return Event.builder()
                    .externalId(source.getExternalShortId())
                    .title(source.getTitle())
                    .subtitle(source.getSubtitle())
                    .description(source.getDescription())
                    .dateTime(source.getDateTime())
                    .provider(provider)
                    .build();
        }
    }
}
