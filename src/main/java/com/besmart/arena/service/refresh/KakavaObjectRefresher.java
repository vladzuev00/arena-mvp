package com.besmart.arena.service.refresh;

import com.besmart.arena.client.KakavaArenaClient;
import com.besmart.arena.client.domain.CategoryTO;
import com.besmart.arena.client.domain.PromoterTO;
import com.besmart.arena.client.domain.ShowTO;
import com.besmart.arena.client.domain.ShowsResponseTO;
import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static com.besmart.arena.util.HtmlUtil.render;
import static java.lang.Double.NaN;

//TODO: remove NOT_DEFINED, refactor and test
@Component
public final class KakavaObjectRefresher extends ArenaObjectRefresher<ShowsResponseTO, CategoryTO, String, PromoterTO, ShowTO, ShowTO, ShowTO> {
    private static final String NOT_DEFINED_STRING = "NOT DEFINED";
    private static final double NOT_DEFINED_DOUBLE = NaN;

    private final KakavaArenaClient client;

    public KakavaObjectRefresher(TransactionTemplate transactionTemplate,
                                 CategoryRepository categoryRepository,
                                 TagRepository tagRepository,
                                 PromoterRepository promoterRepository,
                                 VenueRepository venueRepository,
                                 ShowRepository showRepository,
                                 EventRepository eventRepository,
                                 KakavaArenaClient client) {
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
                .externalId(source.getId())
                .name(NOT_DEFINED_STRING)
                .primaryColor(NOT_DEFINED_STRING)
                .secondaryColor(NOT_DEFINED_STRING)
                .build();
    }

    @Override
    protected Tag createTag(String source) {
        return Tag.builder()
                .name(source)
                .build();
    }

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

    @Override
    protected Show createShow(ShowTO source) {
        return Show.builder()
                .externalShortId(source.getShortId())
                .title(source.getEventTitle())
                .subtitle(NOT_DEFINED_STRING)
                .description(render(source.getEventDescriptionHtml()))
                .venue(Venue.builder().externalId(source.getLocation().getId()).build())
                .imageUrl(source.getEventPicture().getDesktopPictureUrl())
                .promoter(Promoter.builder().externalId(source.getPromoter().getId()).build())
                .build();
    }

    @Override
    protected Event createEvent(ShowTO source) {
        return Event.builder()
                .externalShortId(source.getEventShortId())
                .title(source.getEventTitle())
                .subtitle(NOT_DEFINED_STRING)
                .description(render(source.getEventDescriptionHtml()))
                .dateTime(source.getStartDateTime())
                .show(Show.builder().externalShortId(source.getEventShortId()).build())
                .build();
    }
}
