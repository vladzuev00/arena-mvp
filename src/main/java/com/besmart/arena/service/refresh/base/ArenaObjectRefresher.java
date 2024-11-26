package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import com.besmart.arena.service.cache.ProviderCache;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ArenaObjectRefresher<RESPONSE, CATEGORY_TO, PROMOTER_TO, VENUE_TO, SHOW_TO, EVENT_SOURCE> {
    private final ProviderCache providerCache;
    private final String providerName;
    private final TransactionTemplate transactionTemplate;
    private final CategoryMapper<CATEGORY_TO> categoryMapper;
    private final PromoterMapper<PROMOTER_TO> promoterMapper;
    private final VenueMapper<VENUE_TO> venueMapper;
    private final ShowMapper<SHOW_TO> showMapper;


    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PromoterRepository promoterRepository;
    private final VenueRepository venueRepository;
    private final ShowRepository showRepository;
    private final EventRepository eventRepository;

    public final void refresh() {
        RESPONSE castedResponse = requestObjects();
        refreshWithinNewTransaction(castedResponse);
    }

    protected abstract RESPONSE requestObjects();

    protected abstract List<CATEGORY_TO> getCategorySources(RESPONSE response);

    protected abstract List<TAG_SOURCE> getTagSources(RESPONSE response);

    protected abstract List<PROMOTER_TO> getPromoterSources(RESPONSE response);

    protected abstract List<VENUE_TO> getVenueSources(RESPONSE response);

    protected abstract List<SHOW_TO> getShowSources(RESPONSE response);

    protected abstract List<EVENT_SOURCE> getEventSources(RESPONSE response);

    protected abstract Category createCategory(CATEGORY_TO source);

    protected abstract Tag createTag(TAG_SOURCE source);

    protected abstract Promoter createPromoter(PROMOTER_TO source);

    protected abstract Venue createVenue(VENUE_TO source);

    protected abstract Show createShow(SHOW_TO source, Provider provider);

    protected abstract Event createEvent(EVENT_SOURCE source, Provider provider);

    private void refreshWithinNewTransaction(RESPONSE response) {
        transactionTemplate.executeWithoutResult(
                status -> {
                    Provider provider = getProvider();
                    refreshCategories(response);
                    refreshTags(response);
                    refreshPromoters(response);
                    refreshVenues(response);
                    refreshShows(response, provider);
                    refreshEvents(response, provider);
                }
        );
    }

    private Provider getProvider() {
        return providerCache.get(providerName)
                .orElseThrow(() -> new IllegalStateException("There is no provider '%s'".formatted(providerCache)));
    }

    private void refreshCategories(RESPONSE response) {
        refreshObjects(response, this::getCategorySources, this::createCategory, categoryRepository::refreshByName);
    }

    private void refreshTags(RESPONSE response) {
        refreshObjects(response, this::getTagSources, this::createTag, tagRepository::refreshByName);
    }

    private void refreshPromoters(RESPONSE response) {
        refreshObjects(response, this::getPromoterSources, this::createPromoter, promoterRepository::refreshByName);
    }

    private void refreshVenues(RESPONSE response) {
        refreshObjects(response, this::getVenueSources, this::createVenue, venueRepository::refreshByName);
    }

    private void refreshShows(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getShowSources,
                source -> createShow(source, provider),
                showRepository::refreshByExternalId
        );
    }

    private void refreshEvents(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getEventSources,
                source -> createEvent(source, provider),
                eventRepository::refreshByExternalId
        );
    }

    private <SOURCE, OBJECT> void refreshObjects(RESPONSE response,
                                                 Function<RESPONSE, List<SOURCE>> sourcesGetter,
                                                 Function<SOURCE, OBJECT> objectFactory,
                                                 Consumer<List<OBJECT>> refreshExecutor) {
        List<OBJECT> objects = sourcesGetter.apply(response)
                .stream()
                .map(objectFactory)
                .distinct()
                .toList();
        refreshExecutor.accept(objects);
    }
}