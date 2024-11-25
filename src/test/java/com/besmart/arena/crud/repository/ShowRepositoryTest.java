package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.rowmapper.ShowRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ShowRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private ShowRepository repository;

    @Autowired
    private ShowRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void showsShouldBeRefreshedByExternalId() {
        List<Show> givenShows = List.of(
                Show.builder()
                        .externalShortId("2000")
                        .title("third-title")
                        .subtitle("third-subtitle")
                        .description("third-description")
                        .venue(Venue.builder().name("Šiaulių arena").build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
                        .promoter(Promoter.builder().name("Organizatorius Z, VŠĮ").build())
                        .categories(List.of(Category.builder().name("second-category").build()))
                        .tags(emptyList())
                        .provider(Provider.builder().name("KAKAVA").build())
                        .build(),
                Show.builder()
                        .externalShortId("2002")
                        .title("fourth-title")
                        .subtitle("fourth-subtitle")
                        .description("fourth-description")
                        .venue(Venue.builder().name("Šiaulių arena").build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
                        .promoter(Promoter.builder().name("Organizatorius Z, VŠĮ").build())
                        .categories(List.of(Category.builder().name("second-category").build()))
                        .tags(List.of(Tag.builder().name("first-tag").build(), Tag.builder().name("PROMOTION").build()))
                        .provider(Provider.builder().name("BELETIA").build())
                        .build()
        );

        repository.refreshByExternalId(givenShows);

        Set<Show> actual = findAllShows();
        Set<Show> expected = Set.of(
                new Show(
                        2L,
                        "2002",
                        "fourth-title",
                        "fourth-subtitle",
                        "fourth-description",
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5",
                        Promoter.builder().id(1001L).build(),
                        Provider.builder().id(2L).build(),
                        singletonList(Category.builder().id(1001L).build()),
                        singletonList(Tag.builder().id(1000L).build())
                ),
                new Show(
                        2L,
                        "2002",
                        "fourth-title",
                        "fourth-subtitle",
                        "fourth-description",
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5",
                        Promoter.builder().id(1001L).build(),
                        Provider.builder().id(2L).build(),
                        singletonList(Category.builder().id(1001L).build()),
                        singletonList(Tag.builder().id(1001L).build())
                ),
                new Show(
                        1000L,
                        "2000",
                        "third-title",
                        "third-subtitle",
                        "third-description",
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
                        Promoter.builder().id(1001L).build(),
                        Provider.builder().id(1L).build(),
                        singletonList(Category.builder().id(1001L).build()),
                        singletonList(Tag.builder().id(0L).build())
                ),
                new Show(
                        1001L,
                        "2001",
                        "Dinamika",
                        "second-subtitle",
                        "<a href=\\\\ \"https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-205095f6f\\\" target=\\\\ \"_blank\\\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\\\ \"https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\\\" title=\\\\ \"Lenkų kalba\\\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai.",
                        Venue.builder().id(1000L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3",
                        Promoter.builder().id(1000L).build(),
                        Provider.builder().id(1L).build(),
                        singletonList(Category.builder().id(0L).build()),
                        singletonList(Tag.builder().id(0L).build())
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Show> findAllShows() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                """
                        SELECT
                        	shows.id AS showId,
                        	shows.external_short_id AS showExternalShortId,
                        	shows.title AS showTitle,
                        	shows.subtitle AS showSubtitle,
                        	shows.description AS showDescription,
                        	shows.venue_id AS showVenueId,
                        	shows.image_url AS showImageUrl,
                        	shows.promoter_id AS showPromoterId,
                        	shows.provider_id AS showProviderId,
                        	shows_categories.category_id AS showCategoryId,
                        	shows_tags.tag_id AS showTagId
                        FROM shows
                        LEFT OUTER JOIN shows_categories ON shows.id = shows_categories.show_id
                        LEFT OUTER JOIN shows_tags ON shows.id = shows_tags.show_id"""
        );
    }
}
