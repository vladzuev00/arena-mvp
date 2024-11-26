package com.besmart.arena.service.refresh;

import com.besmart.arena.client.KakavaArenaClient;
import com.besmart.arena.client.domain.CategoryTO;
import com.besmart.arena.client.domain.PromoterTO;
import com.besmart.arena.client.domain.ShowTO;
import com.besmart.arena.client.domain.ShowsResponseTO;
import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import com.besmart.arena.service.cache.ProviderCache;
import com.besmart.arena.service.refresh.base.ArenaObjectRefresher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static com.besmart.arena.util.HtmlUtil.render;
import static java.lang.Double.NaN;

@Component
public final class KakavaObjectRefresher extends ArenaObjectRefresher<ShowsResponseTO, CategoryTO, String, PromoterTO, ShowTO, ShowTO, ShowTO> {
    private static final String PROVIDER_NAME = "KAKAVA";

    //TODO: remove
    static final String NOT_DEFINED_STRING = "NOT DEFINED";

    //TODO: remove
    static final double NOT_DEFINED_DOUBLE = NaN;

    private final KakavaArenaClient client;

    public KakavaObjectRefresher(ProviderCache providerCache,
                                 TransactionTemplate transactionTemplate,
                                 CategoryRepository categoryRepository,
                                 TagRepository tagRepository,
                                 PromoterRepository promoterRepository,
                                 VenueRepository venueRepository,
                                 ShowRepository showRepository,
                                 EventRepository eventRepository,
                                 KakavaArenaClient client) {
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
    protected ShowsResponseTO requestObjects() {
        return client.requestShows();
    }

    @Override
    protected List<CategoryTO> getCategorySources(ShowsResponseTO response) {
        return response.getShows()
                .stream()
                .flatMap(show -> show.getEventCategories().stream())
                .toList();
    }

    @Override
    protected List<String> getTagSources(ShowsResponseTO response) {
        return response.getShows()
                .stream()
                .flatMap(show -> show.getMarks().stream())
                .toList();
    }

    @Override
    protected List<PromoterTO> getPromoterSources(ShowsResponseTO response) {
        return response.getShows()
                .stream()
                .map(ShowTO::getPromoter)
                .toList();
    }

    @Override
    protected List<ShowTO> getVenueSources(ShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected List<ShowTO> getShowSources(ShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected List<ShowTO> getEventSources(ShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected Category createCategory(CategoryTO source) {
        return Category.builder()
                .name(Integer.toString(source.getId()))
                .primaryColor(NOT_DEFINED_STRING)
                .secondaryColor(NOT_DEFINED_STRING)
                .build();
    }

    @Override
    protected Tag createTag(String source) {
        return Tag.builder().name(source).build();
    }

    @Override
    protected Promoter createPromoter(PromoterTO source) {
        return Promoter.builder()
                .name(source.getName())
                .iconUrl(NOT_DEFINED_STRING)
                .webPageUrl(NOT_DEFINED_STRING)
                .build();
    }

    @Override
    protected Venue createVenue(ShowTO source) {
        return Venue.builder()
                .name(source.getLocation().getName())
                .address(source.getVenueAddress())
                .latitude(NOT_DEFINED_DOUBLE)
                .longitude(NOT_DEFINED_DOUBLE)
                .build();
    }

    @Override
    protected Show createShow(ShowTO source, Provider provider) {
        return Show.builder()
                .externalShortId(Integer.toString(source.getShortId()))
                .title(source.getEventTitle())
                .subtitle(NOT_DEFINED_STRING)
                .description(render(source.getEventDescriptionHtml()))
                .venue(getVenueOnlyWithName(source))
                .imageUrl(source.getEventPicture().getDesktopPictureUrl())
                .promoter(getPromoterOnlyWithName(source))
                .provider(provider)
                .categories(getCategoriesOnlyWithName(source))
                .tags(getTagsOnlyWithName(source))
                .build();
    }

    @Override
    protected Event createEvent(ShowTO source, Provider provider) {
        return Event.builder()
                .externalShortId(Integer.toString(source.getEventShortId()))
                .title(source.getEventTitle())
                .subtitle(NOT_DEFINED_STRING)
                .description(render(source.getEventDescriptionHtml()))
                .dateTime(source.getStartDateTime())
                .show(getShowOnlyWithExternalId(source))
                .provider(provider)
                .build();
    }

    private Venue getVenueOnlyWithName(ShowTO source) {
        return Venue.builder().name(source.getLocation().getName()).build();
    }

    private Promoter getPromoterOnlyWithName(ShowTO source) {
        return Promoter.builder().name(source.getPromoter().getName()).build();
    }

    private Show getShowOnlyWithExternalId(ShowTO source) {
        return Show.builder().externalShortId(Integer.toString(source.getShortId())).build();
    }

    private List<Category> getCategoriesOnlyWithName(ShowTO source) {
        return source.getEventCategories()
                .stream()
                .map(categorySource -> Category.builder().name(Integer.toString(categorySource.getId())).build())
                .toList();
    }

    private List<Tag> getTagsOnlyWithName(ShowTO source) {
        return source.getMarks()
                .stream()
                .map(this::createTag)
                .toList();
    }
}
