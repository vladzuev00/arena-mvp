package com.besmart.arena.service.refresh;

import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ArenaObjectRefresher<RESPONSE, CATEGORY_TO, TAG_TO, PROMOTER_TO, VENUE_TO, SHOW_TO, EVENT_TO> {
    private final Class<RESPONSE> responseType;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PromoterRepository promoterRepository;
    private final VenueRepository venueRepository;
    private final ShowRepository showRepository;
    private final EventRepository eventRepository;

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

    protected abstract List<CATEGORY_TO> getCategoryTos(RESPONSE response);

    protected abstract List<TAG_TO> getTagTos(RESPONSE response);

    protected abstract List<PROMOTER_TO> getPromoterTos(RESPONSE response);

    protected abstract List<VENUE_TO> getVenueTos(RESPONSE response);

    protected abstract List<SHOW_TO> getShowTos(RESPONSE response);

    protected abstract List<EVENT_TO> getEventTos(RESPONSE response);

    protected abstract Category createCategory(CATEGORY_TO to);

    protected abstract Tag createTag(TAG_TO to);

    protected abstract Promoter createPromoter(PROMOTER_TO to);

    protected abstract Venue createVenue(VENUE_TO to);

    protected abstract Show createShow(SHOW_TO to);

    protected abstract Event createEvent(EVENT_TO to);

    private void refreshCategories(RESPONSE response) {
        refreshObjects(response, this::getCategoryTos, this::createCategory, categoryRepository::refreshByExternalId);
    }

    private void refreshTags(RESPONSE response) {
        refreshObjects(response, this::getTagTos, this::createTag, tagRepository::refreshByExternalId);
    }

    private void refreshPromoters(RESPONSE response) {
        refreshObjects(response, this::getPromoterTos, this::createPromoter, promoterRepository::refreshByExternalId);
    }

    private void refreshVenues(RESPONSE response) {
        refreshObjects(response, this::getVenueTos, this::createVenue, venueRepository::refreshByExternalId);
    }

    private void refreshShows(RESPONSE response) {
        refreshObjects(response, this::getShowTos, this::createShow, showRepository::refreshByExternalId);
    }

    private void refreshEvents(RESPONSE response) {
        refreshObjects(response, this::getEventTos, this::createEvent, eventRepository::refreshByExternalId);
    }

    private <TO, OBJECT> void refreshObjects(RESPONSE response,
                                             Function<RESPONSE, List<TO>> tosGetter,
                                             Function<TO, OBJECT> objectFactory,
                                             Consumer<List<OBJECT>> refreshExecutor) {
        List<OBJECT> objects = tosGetter.apply(response)
                .stream()
                .map(objectFactory)
                .distinct()
                .toList();
        refreshExecutor.accept(objects);
    }
}
