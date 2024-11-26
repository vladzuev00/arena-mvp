package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.repository.CategoryRepository;
import com.besmart.arena.crud.repository.EventRepository;
import com.besmart.arena.crud.repository.PromoterRepository;
import com.besmart.arena.crud.repository.ShowRepository;
import com.besmart.arena.service.cache.ProviderCache;
import com.besmart.arena.service.cache.VenueCache;
import com.besmart.arena.service.refresh.base.mapper.CategoryMapper;
import com.besmart.arena.service.refresh.base.mapper.EventMapper;
import com.besmart.arena.service.refresh.base.mapper.PromoterMapper;
import com.besmart.arena.service.refresh.base.mapper.ShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class ArenaObjectRefresher<RESPONSE, CATEGORY_TO, PROMOTER_TO, SHOW_TO, EVENT_TO> {
    private final ProviderCache providerCache;
    private final VenueCache venueCache;
    private final String providerName;
    private final TransactionTemplate transactionTemplate;
    private final CategoryMapper<CATEGORY_TO> categoryMapper;
    private final PromoterMapper<PROMOTER_TO> promoterMapper;
    private final ShowMapper<SHOW_TO> showMapper;
    private final EventMapper<EVENT_TO> eventMapper;
    private final CategoryRepository categoryRepository;
    private final PromoterRepository promoterRepository;
    private final ShowRepository showRepository;
    private final EventRepository eventRepository;

    public final void refresh() {
        RESPONSE response = requestObjects();
        refreshWithinNewTransaction(response);
    }

    protected abstract RESPONSE requestObjects();

    protected abstract List<CATEGORY_TO> getCategoryTos(RESPONSE response);

    protected abstract List<PROMOTER_TO> getPromoterTos(RESPONSE response);

    protected abstract List<SHOW_TO> getShowTos(RESPONSE response);

    protected abstract List<EVENT_TO> getEventTos(RESPONSE response);

    private void refreshWithinNewTransaction(RESPONSE response) {
        transactionTemplate.executeWithoutResult(
                status -> {
                    Provider provider = getProvider();
                    refreshCategories(response, provider);
                    refreshPromoters(response, provider);
                    refreshShows(response, provider);
                    refreshEvents(response, provider);
                }
        );
    }

    private Provider getProvider() {
        return providerCache.get(providerName)
                .orElseThrow(() -> new IllegalStateException("There is no provider '%s'".formatted(providerCache)));
    }

    private void refreshCategories(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getCategoryTos,
                to -> categoryMapper.map(to, provider),
                categoryRepository::refreshByExternalId
        );
    }

    private void refreshPromoters(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getPromoterTos,
                to -> promoterMapper.map(to, provider),
                promoterRepository::refreshByExternalId
        );
    }

    private void refreshShows(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getShowTos,
                to -> showMapper.map(to, provider, venueCache.getVenue()),
                showRepository::refreshByExternalId
        );
    }

    private void refreshEvents(RESPONSE response, Provider provider) {
        refreshObjects(
                response,
                this::getEventTos,
                to -> eventMapper.map(to, provider),
                eventRepository::refreshByExternalId
        );
    }

    private <TO, OBJECT> void refreshObjects(RESPONSE response,
                                             Function<RESPONSE, List<TO>> tosGetter,
                                             Function<TO, OBJECT> objectMapper,
                                             Consumer<List<OBJECT>> refreshExecutor) {
        List<OBJECT> objects = tosGetter.apply(response)
                .stream()
                .map(objectMapper)
                .distinct()
                .toList();
        refreshExecutor.accept(objects);
    }
}
