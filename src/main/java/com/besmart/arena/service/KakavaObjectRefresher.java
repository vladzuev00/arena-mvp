package com.besmart.arena.service;

import com.besmart.arena.client.domain.ShowsResponseTO;
import com.besmart.arena.crud.dto.*;
import com.besmart.arena.crud.service.*;
import com.besmart.arena.client.domain.CategoryTO;
import com.besmart.arena.client.domain.PromoterTO;
import com.besmart.arena.client.domain.ShowTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.besmart.arena.util.HtmlUtil.render;

//TODO: refactor
@Component
public final class KakavaObjectRefresher extends ArenaObjectRefresher<
        ShowsResponseTO,
        CategoryTO,
        ShowTO,
        PromoterTO,
        ShowTO,
        ShowTO,
        ShowTO
        > {

    public KakavaObjectRefresher(CategoryService categoryService,
                                 TagService tagService,
                                 PromoterService promoterService,
                                 VenueService venueService,
                                 ShowService showService,
                                 EventService eventService) {
        super(
                ShowsResponseTO.class,
                categoryService,
                tagService,
                promoterService,
                venueService,
                showService,
                eventService
        );
    }

    @Override
    protected List<CategoryTO> getCategorySources(ShowsResponseTO response) {
        return response.getShows().stream()
                .flatMap(show -> show.getEventCategories().stream())
                .toList();
    }

    @Override
    protected List<ShowTO> getTagSources(ShowsResponseTO response) {
        return response.getShows();
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

    //TODO: откуда брать name, primaryColor, secondaryColor
    @Override
    protected Category createCategory(CategoryTO source) {
        return Category.builder()
                .externalId(source.getId())
                .build();
    }

    //TODO: у Тихана извлекаются marks и customMarks, так нужно ли делать
    @Override
    protected Tag createTag(ShowTO source) {
        throw new UnsupportedOperationException();
    }

    //TODO: что делать с iconUrl и webPageUrl
    @Override
    protected Promoter createPromoter(PromoterTO source) {
        return Promoter.builder()
                .externalId(source.getId())
                .name(source.getName())
                .build();
    }

    //TODO: что делать c latitude и longitude
    @Override
    protected Venue createVenue(ShowTO source) {
        return Venue.builder()
                .externalId(source.getLocation().getId())
                .name(source.getLocation().getName())
                .address(source.getVenueAddress())
                .build();
    }

    //TODO: что должно быть в subtitle
    //TODO: что делать с категориями: у Тихана только 1 категория может быть у Show, от provider-а приходит массив
    //TODO: что делать с Venue - нет externalId
    @Override
    protected Show createShow(ShowTO source) {
        return Show.builder()
                .externalShortId(source.getShortId())
                .title(source.getEventTitle())
                .description(render(source.getEventDescriptionHtml()))
                .imageUrl(source.getEventPicture().getDesktopPictureUrl())
                .build();
    }

    //TODO: что должно быть в subtitle
    @Override
    protected Event createEvent(ShowTO source) {
        return Event.builder()
                .externalShortId(source.getEventShortId())
                .title(source.getEventTitle())
                .description(render(source.getEventDescriptionHtml()))
                .dateTime(source.getStartDateTime())
                .show(Show.builder().externalShortId(source.getEventShortId()).build())
                .build();
    }
}
