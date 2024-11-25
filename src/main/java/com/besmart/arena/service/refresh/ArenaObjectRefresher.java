package com.besmart.arena.service.refresh;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ArenaObjectRefresher<RESPONSE, CATEGORY_SOURCE, TAG_SOURCE, PROMOTER_SOURCE, VENUE_SOURCE, SHOW_SOURCE, EVENT_SOURCE> {
    private final TransactionTemplate transactionTemplate;
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

    protected abstract List<CATEGORY_SOURCE> getCategorySources(RESPONSE response);

    protected abstract List<TAG_SOURCE> getTagSources(RESPONSE response);

    protected abstract List<PROMOTER_SOURCE> getPromoterSources(RESPONSE response);

    protected abstract List<VENUE_SOURCE> getVenueSources(RESPONSE response);

    protected abstract List<SHOW_SOURCE> getShowSources(RESPONSE response);

    protected abstract List<EVENT_SOURCE> getEventSources(RESPONSE response);

    protected abstract Category createCategory(CATEGORY_SOURCE source);

    protected abstract Tag createTag(TAG_SOURCE source);

    protected abstract Promoter createPromoter(PROMOTER_SOURCE source);

    protected abstract Venue createVenue(VENUE_SOURCE source);

    protected abstract Show createShow(SHOW_SOURCE source);

    protected abstract Event createEvent(EVENT_SOURCE source);

    private void refreshWithinNewTransaction(RESPONSE response) {
        transactionTemplate.executeWithoutResult(
                status -> {
                    refreshCategories(response);
                    refreshTags(response);
                    refreshPromoters(response);
                    refreshVenues(response);
                    refreshShows(response);
                    refreshEvents(response);
                }
        );
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
        refreshObjects(response, this::getVenueSources, this::createVenue, venueRepository::refreshByExternalId);
    }

    private void refreshShows(RESPONSE response) {
        refreshObjects(response, this::getShowSources, this::createShow, showRepository::refreshByExternalId);
    }

    private void refreshEvents(RESPONSE response) {
        refreshObjects(response, this::getEventSources, this::createEvent, eventRepository::refreshByExternalId);
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
