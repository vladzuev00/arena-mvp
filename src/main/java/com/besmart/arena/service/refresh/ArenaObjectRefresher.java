package com.besmart.arena.service.refresh;

import com.besmart.arena.crud.dto.*;
import com.besmart.arena.crud.service.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ArenaObjectRefresher<RESPONSE, CATEGORY_SOURCE, TAG_SOURCE, PROMOTER_SOURCE, VENUE_SOURCE, SHOW_SOURCE, EVENT_SOURCE> {
    private final Class<RESPONSE> responseType;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final PromoterService promoterService;
    private final VenueService venueService;
    private final ShowService showService;
    private final EventService eventService;

    public final boolean isSuitableResponse(Object response) {
        return responseType.isInstance(response);
    }

    public final void refresh(Object response) {
        RESPONSE castedResponse = responseType.cast(response);
        refreshCategories(castedResponse);
        refreshTags(castedResponse);
        refreshPromoters(castedResponse);
        refreshVenues(castedResponse);
        refreshShows(castedResponse);
        refreshEvents(castedResponse);
    }

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

    protected abstract Show createShow(SHOW_SOURCE show);

    protected abstract Event createEvent(EVENT_SOURCE source);

    private void refreshCategories(RESPONSE response) {
        refreshObjects(response, this::getCategorySources, this::createCategory, categoryService::refreshByExternalId);
    }

    private void refreshTags(RESPONSE response) {
        refreshObjects(response, this::getTagSources, this::createTag, tagService::refreshByExternalId);
    }

    private void refreshPromoters(RESPONSE response) {
        refreshObjects(response, this::getPromoterSources, this::createPromoter, promoterService::refreshByExternalId);
    }

    private void refreshVenues(RESPONSE response) {
        refreshObjects(response, this::getVenueSources, this::createVenue, venueService::refreshByExternalId);
    }

    private void refreshShows(RESPONSE response) {
        refreshObjects(response, this::getShowSources, this::createShow, showService::refreshByExternalId);
    }

    private void refreshEvents(RESPONSE response) {
        refreshObjects(response, this::getEventSources, this::createEvent, eventService::refreshByExternalId);
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
