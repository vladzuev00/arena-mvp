package com.besmart.arena.service.refresh;

import com.besmart.arena.client.domain.CategoryTO;
import com.besmart.arena.client.domain.PromoterTO;
import com.besmart.arena.client.domain.ShowTO;
import com.besmart.arena.client.domain.ShowsResponseTO;
import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.besmart.arena.util.HtmlUtil.render;
import static java.lang.Double.NaN;

//TODO: remove NOT_DEFINED, refactor and test
@Component
public final class KakavaObjectRefresher extends ArenaObjectRefresher<ShowsResponseTO, CategoryTO, ShowTO, PromoterTO, ShowTO, ShowTO, ShowTO> {
    private static final String NOT_DEFINED_STRING = "NOT DEFINED";
    private static final double NOT_DEFINED_DOUBLE = NaN;

    public KakavaObjectRefresher(CategoryRepository categoryRepository,
                                 TagRepository tagRepository,
                                 PromoterRepository promoterRepository,
                                 VenueRepository venueRepository,
                                 ShowRepository showRepository,
                                 EventRepository eventRepository) {
        super(
                ShowsResponseTO.class,
                categoryRepository,
                tagRepository,
                promoterRepository,
                venueRepository,
                showRepository,
                eventRepository
        );
    }

    @Override
    protected List<CategoryTO> getCategorySources(ShowsResponseTO response) {
        return response.getShows()
                .stream()
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

    @Override
    protected Category createCategory(CategoryTO source) {
        return Category.builder()
                .externalId(source.getId())
                .name(NOT_DEFINED_STRING)
                .primaryColor(NOT_DEFINED_STRING)
                .secondaryColor(NOT_DEFINED_STRING)
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
                .iconUrl(NOT_DEFINED_STRING)
                .webPageUrl(NOT_DEFINED_STRING)
                .build();
    }

    @Override
    protected Venue createVenue(ShowTO source) {
        return Venue.builder()
                .externalId(source.getLocation().getId())
                .name(source.getLocation().getName())
                .address(source.getVenueAddress())
                .latitude(NOT_DEFINED_DOUBLE)
                .longitude(NOT_DEFINED_DOUBLE)
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
