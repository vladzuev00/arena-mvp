package com.besmart.arena.service.refresh;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
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
import java.util.UUID;
import java.util.function.Consumer;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class ArenaObjectRefresherTest {

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
                                2334,
                                "first-event-title",
                                "first-event-subtitle",
                                "first-event-description",
                                LocalDateTime.of(2024, 11, 24, 9, 11, 12),
                                new TestShowSource(
                                        2335,
                                        "first-show-title",
                                        "first-show-subtitle",
                                        "first-show-description",
                                        new TestVenueSource(
                                                fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"),
                                                "first-venue-name",
                                                "first-venue-address",
                                                5.5,
                                                6.6
                                        ),
                                        "first-show-image-url",
                                        new TestPromoterSource(
                                                fromString("a60de864-5c52-11ee-a81c-000d3aa868a3"),
                                                "first-promoter-name",
                                                "first-promoter-icon-url",
                                                "first-promoter-web-page-url"
                                        ),
                                        List.of(
                                                new TestCategorySource(
                                                        2336,
                                                        "first-category-name",
                                                        "first-category-primary-color",
                                                        "first-category-secondary-color"
                                                ),
                                                new TestCategorySource(
                                                        2337,
                                                        "second-category-name",
                                                        "second-category-primary-color",
                                                        "second-category-secondary-color"
                                                )
                                        ),
                                        List.of(new TestTagSource("first-tag"), new TestTagSource("second-tag"))
                                )
                        ),
                        new TestEventSource(
                                2338,
                                "second-event-title",
                                "second-event-subtitle",
                                "second-event-description",
                                LocalDateTime.of(2024, 11, 24, 9, 11, 13),
                                new TestShowSource(
                                        2339,
                                        "second-show-title",
                                        "second-show-subtitle",
                                        "second-show-description",
                                        new TestVenueSource(
                                                fromString("a60de864-5c52-11ee-a81c-000d3aa868a3"),
                                                "second-venue-name",
                                                "second-venue-address",
                                                7.7,
                                                8.8
                                        ),
                                        "second-show-image-url",
                                        new TestPromoterSource(
                                                fromString("a60de864-5c52-11ee-a81c-000d3aa868a4"),
                                                "second-promoter-name",
                                                "second-promoter-icon-url",
                                                "second-promoter-web-page-url"
                                        ),
                                        List.of(
                                                new TestCategorySource(
                                                        2340,
                                                        "third-category-name",
                                                        "third-category-primary-color",
                                                        "third-category-secondary-color"
                                                ),
                                                new TestCategorySource(
                                                        2341,
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

        verify(mockedTransactionTemplate, times(1)).executeWithoutResult(transactionAction.capture());
        transactionAction.getValue().accept(null);

        verify(mockedCategoryRepository, times(1)).refreshByExternalId(categoriesArgumentCaptor.capture());
        List<Category> actualCategories = categoriesArgumentCaptor.getValue();
        List<Category> expectedCategories = List.of(
                Category.builder()
                        .externalId(2336)
                        .name("first-category-name")
                        .primaryColor("first-category-primary-color")
                        .secondaryColor("first-category-secondary-color")
                        .build(),
                Category.builder()
                        .externalId(2337)
                        .name("second-category-name")
                        .primaryColor("second-category-primary-color")
                        .secondaryColor("second-category-secondary-color")
                        .build(),
                Category.builder()
                        .externalId(2340)
                        .name("third-category-name")
                        .primaryColor("third-category-primary-color")
                        .secondaryColor("third-category-secondary-color")
                        .build(),
                Category.builder()
                        .externalId(2341)
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
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3"))
                        .name("first-promoter-name")
                        .iconUrl("first-promoter-icon-url")
                        .webPageUrl("first-promoter-web-page-url")
                        .build(),
                Promoter.builder()
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a4"))
                        .name("second-promoter-name")
                        .iconUrl("second-promoter-icon-url")
                        .webPageUrl("second-promoter-web-page-url")
                        .build()
        );
        assertEquals(expectedPromoters, actualPromoters);

        verify(mockedVenueRepository, times(1)).refreshByExternalId(venuesArgumentCaptor.capture());
        List<Venue> actualVenues = venuesArgumentCaptor.getValue();
        List<Venue> expectedVenues = List.of(
                Venue.builder()
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a2"))
                        .name("first-venue-name")
                        .address("first-venue-address")
                        .latitude(5.5)
                        .longitude(6.6)
                        .build(),
                Venue.builder()
                        .externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3"))
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
                        .externalShortId(2335)
                        .title("first-show-title")
                        .subtitle("first-show-subtitle")
                        .description("first-show-description")
                        .imageUrl("first-show-image-url")
                        .build(),
                Show.builder()
                        .externalShortId(2339)
                        .title("second-show-title")
                        .subtitle("second-show-subtitle")
                        .description("second-show-description")
                        .imageUrl("second-show-image-url")
                        .build()
        );
        assertEquals(expectedShows, actualShows);

        verify(mockedEventRepository, times(1)).refreshByExternalId(eventsArgumentCaptor.capture());
        List<Event> actualEvents = eventsArgumentCaptor.getValue();
        List<Event> expectedEvents = List.of(
                Event.builder()
                        .externalShortId(2334)
                        .title("first-event-title")
                        .subtitle("first-event-subtitle")
                        .description("first-event-description")
                        .dateTime(LocalDateTime.of(2024, 11, 24, 9, 11, 12))
                        .build(),
                Event.builder()
                        .externalShortId(2338)
                        .title("second-event-title")
                        .subtitle("second-event-subtitle")
                        .description("second-event-description")
                        .dateTime(LocalDateTime.of(2024, 11, 24, 9, 11, 13))
                        .build()
        );
        assertEquals(expectedEvents, actualEvents);
    }

    @Value
    private static class TestCategorySource {
        int externalId;
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
        UUID externalId;
        String name;
        String iconUrl;
        String webPageUrl;
    }

    @Value
    private static class TestVenueSource {
        UUID externalId;
        String name;
        String address;
        double latitude;
        double longitude;
    }

    @Value
    private static class TestShowSource {
        int externalShortId;
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
        int externalShortId;
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
        private final TestClient client;

        public TestArenaObjectRefresher(TransactionTemplate transactionTemplate,
                                        CategoryRepository categoryRepository,
                                        TagRepository tagRepository,
                                        PromoterRepository promoterRepository,
                                        VenueRepository venueRepository,
                                        ShowRepository showRepository,
                                        EventRepository eventRepository,
                                        TestClient client) {
            super(
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
        protected List<TestCategorySource> getCategorySources(TestResponse response) {
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
        protected List<TestPromoterSource> getPromoterSources(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(event -> event.getShow().getPromoter())
                    .toList();
        }

        @Override
        protected List<TestVenueSource> getVenueSources(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(event -> event.getShow().getVenue())
                    .toList();
        }

        @Override
        protected List<TestShowSource> getShowSources(TestResponse response) {
            return response.getEvents()
                    .stream()
                    .map(TestEventSource::getShow)
                    .toList();
        }

        @Override
        protected List<TestEventSource> getEventSources(TestResponse response) {
            return response.getEvents();
        }

        @Override
        protected Category createCategory(TestCategorySource source) {
            return Category.builder()
                    .externalId(source.getExternalId())
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
                    .externalId(source.getExternalId())
                    .name(source.getName())
                    .iconUrl(source.getIconUrl())
                    .webPageUrl(source.getWebPageUrl())
                    .build();
        }

        @Override
        protected Venue createVenue(TestVenueSource source) {
            return Venue.builder()
                    .externalId(source.getExternalId())
                    .name(source.getName())
                    .address(source.getAddress())
                    .latitude(source.getLatitude())
                    .longitude(source.getLongitude())
                    .build();
        }

        @Override
        protected Show createShow(TestShowSource source) {
            return Show.builder()
                    .externalShortId(source.getExternalShortId())
                    .title(source.getTitle())
                    .subtitle(source.getSubtitle())
                    .description(source.getDescription())
                    .imageUrl(source.getImageUrl())
                    .build();
        }

        @Override
        protected Event createEvent(TestEventSource source) {
            return Event.builder()
                    .externalShortId(source.getExternalShortId())
                    .title(source.getTitle())
                    .subtitle(source.getSubtitle())
                    .description(source.getDescription())
                    .dateTime(source.getDateTime())
                    .build();
        }
    }
}
