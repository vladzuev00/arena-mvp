package com.besmart.arena.service;

import com.besmart.arena.client.domain.KakavaShowsResponseTO;
import com.besmart.arena.crud.dto.*;
import com.besmart.arena.crud.service.*;
import com.besmart.arena.domain.CategoryTO;
import com.besmart.arena.domain.PromoterTO;
import com.besmart.arena.domain.ShowTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class KakavaObjectRefresher extends ArenaObjectRefresher<
        KakavaShowsResponseTO,
        CategoryTO,
        ShowTO,
        PromoterTO,
        ShowTO,
        ShowTO,
        ShowTO
        > {
    private final HtmlRenderer htmlRenderer;

    public KakavaObjectRefresher(CategoryService categoryService,
                                 TagService tagService,
                                 PromoterService promoterService,
                                 VenueService venueService,
                                 ShowService showService,
                                 EventService eventService,
                                 HtmlRenderer htmlRenderer) {
        super(
                KakavaShowsResponseTO.class,
                categoryService,
                tagService,
                promoterService,
                venueService,
                showService,
                eventService
        );
        this.htmlRenderer = htmlRenderer;
    }

    @Override
    protected List<CategoryTO> getCategorySources(KakavaShowsResponseTO response) {
        return response.getShows().stream()
                .flatMap(show -> show.getEventCategories().stream())
                .toList();
    }

    @Override
    protected List<ShowTO> getTagSources(KakavaShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected List<PromoterTO> getPromoterSources(KakavaShowsResponseTO response) {
        return response.getShows()
                .stream()
                .map(ShowTO::getPromoter)
                .toList();
    }

    @Override
    protected List<ShowTO> getVenueSources(KakavaShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected List<ShowTO> getShowSources(KakavaShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected List<ShowTO> getEventSources(KakavaShowsResponseTO response) {
        return response.getShows();
    }

    @Override
    protected Category createCategory(CategoryTO source) {
        //TODO: откуда брать name, primaryColor, secondaryColor
//        return new Category(source.getId());
        throw new UnsupportedOperationException();
    }

    @Override
    protected Tag createTag(ShowTO showTO) {
        return null;
    }

    @Override
    protected Promoter createPromoter(PromoterTO promoterTO) {
        return null;
    }

    @Override
    protected Venue createVenue(ShowTO showTO) {
        return null;
    }

    @Override
    protected Show createShow(ShowTO show) {
        return null;
    }

    //TODO: что должно быть в subtitle
    @Override
    protected Event createEvent(ShowTO source) {
        return Event.builder()
                .externalShortId(source.getEventShortId())
                .title(source.getEventTitle())
                .description(htmlRenderer.render(source.getEventDescriptionHtml()))
                .dateTime(source.getStartDateTime())
                .show(Show.builder().externalId(source.getEventShortId()).build())
                .build();
    }
}
