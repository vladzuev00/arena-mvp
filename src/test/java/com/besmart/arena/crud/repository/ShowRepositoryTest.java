package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.*;
import com.besmart.arena.crud.rowmapper.ShowRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static java.util.Collections.emptyList;
import static java.util.UUID.fromString;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
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
                        .externalShortId(2000)
                        .title("third-title")
                        .subtitle("third-subtitle")
                        .description("third-description")
                        .venue(Venue.builder().externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
                        .promoter(Promoter.builder().externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29938e")).build())
                        .categories(List.of(Category.builder().externalId(2001).build()))
                        .tags(emptyList())
                        .build(),
                Show.builder()
                        .externalShortId(2002)
                        .title("fourth-title")
                        .subtitle("fourth-subtitle")
                        .description("fourth-description")
                        .venue(Venue.builder().externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
                        .promoter(Promoter.builder().externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29938e")).build())
                        .categories(List.of(Category.builder().externalId(2001).build()))
                        .tags(List.of(Tag.builder().name("first-tag").build(), Tag.builder().name("PROMOTION").build()))
                        .build()
        );

        repository.refreshByExternalId(givenShows);

        Set<Show> actual = findAllShows();
        //[Show(id=2, externalShortId=2002, title=fourth-title, subtitle=fourth-subtitle, description=fourth-description, venue=Venue(id=1001, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5, promoter=Promoter(id=1001, externalId=null, name=null, iconUrl=null, webPageUrl=null), categories=[Category(id=2, externalId=2001, name=PROMOTION, primaryColor=#FFFFFFAA, secondaryColor=#56135a)], tags=[Tag(id=2, name=PROMOTION)]),
        // Show(id=2, externalShortId=2002, title=fourth-title, subtitle=fourth-subtitle, description=fourth-description, venue=Venue(id=1001, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5, promoter=Promoter(id=1001, externalId=null, name=null, iconUrl=null, webPageUrl=null), categories=[Category(id=2, externalId=2001, name=first-tag, primaryColor=#FFFFFFAA, secondaryColor=#56135a)], tags=[Tag(id=2, name=first-tag)]),
        // Show(id=1000, externalShortId=2000, title=third-title, subtitle=third-subtitle, description=third-description, venue=Venue(id=1001, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4, promoter=Promoter(id=1001, externalId=null, name=null, iconUrl=null, webPageUrl=null), categories=[Category(id=1000, externalId=2001, name=null, primaryColor=#FFFFFFAA, secondaryColor=#56135a)], tags=[Tag(id=1000, name=null)]),
        // Show(id=1001, externalShortId=2001, title=Dinamika, subtitle=second-subtitle, description=<a href=\\ "https://cms.kakava.lt/api/assets/7b0d4de6-aa49-4bd6-bb9b-6c5f106118a3?q=d7fcefda-248a-0f6e-bc3e-205095f6f\" target=\\ "_blank\"> taisyklės  Rajoną pavadinti  Pilaite  nuspręsta ne iš karto. Buvo siūlomi įvairūs vietovardžio variantai, pavyzdžiui  Sudervėlė . Kalbininkui Jonui Jurkštui ir parkotyrininkui Kęstučiui Labanauskui pavyko įtikinti, kad šis pavadinimas yra autentiškas.  Pilaitė – istorinis vietovardis, kilęs iš bendrinio žodžio „pilis“. Netoli Pilaitės XIX a. buvo dvi gyvenvietės: Papilėnai ir Papilaičiai. Pasak Jono Jurkšto, jie žymi vietas, esančias prie pilies (pilaitės), ir patvirtina, kad čia yra buvęs vietovardis  Pilaitė  ir tikrai yra stovėjusi pilis. Todėl  <a href=\\ "https://lt.wikipedia.org/wiki/Lenk%C5%B3_kalba\" title=\\ "Lenkų kalba\"> lenk.   Zameczek  yra vertinys iš žodžio pilaitė, o ne atvirkščiai, kaip manė kai kurie tyrinėtojai., venue=Venue(id=1000, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3, promoter=Promoter(id=1000, externalId=null, name=null, iconUrl=null, webPageUrl=null), categories=[Category(id=1001, externalId=0, name=null, primaryColor=null, secondaryColor=null)], tags=[Tag(id=1001, name=null)])]
        Set<Show> expected = Set.of(

        );
        assertEquals(expected, actual);
    }

    private Set<Show> findAllShows() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                """
                        SELECT
                        	shows.id, shows.external_short_id, shows.title, shows.subtitle, shows.description, shows.venue_id, shows.image_url, shows.promoter_id,
                        	tags.id, tags.name,
                        	categories.id, categories.external_id, categories.name, categories.primary_color, categories.secondary_color
                        FROM shows
                        LEFT OUTER JOIN shows_tags ON shows.id = shows_tags.show_id
                        LEFT OUTER JOIN tags ON tags.id = shows_tags.tag_id
                        LEFT OUTER JOIN shows_categories ON shows.id = shows_categories.show_id
                        LEFT OUTER JOIN categories ON categories.id = shows_categories.category_id"""
        );
    }
}
