package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Category;
import com.besmart.arena.crud.dto.Show;
import com.besmart.arena.crud.dto.Venue;
import com.besmart.arena.crud.rowmapper.ShowRowMapper;
import com.besmart.arena.testutil.JdbcTemplateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static java.util.UUID.fromString;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ShowServiceTest extends AbstractSpringBootTest {

    @Autowired
    private ShowService service;

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
                        .category(Category.builder().externalId(2001).build())
                        .venue(Venue.builder().externalId(fromString("550e8400-e29b-41d4-a716-446655440001")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
                        .build(),
                Show.builder()
                        .externalShortId(2002)
                        .title("fourth-title")
                        .subtitle("fourth-subtitle")
                        .description("fourth-description")
                        .category(Category.builder().externalId(2001).build())
                        .venue(Venue.builder().externalId(fromString("550e8400-e29b-41d4-a716-446655440001")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
                        .build()
        );

        service.refreshByExternalId(givenShows);

        Set<Show> actual = findAllShows();
        Set<Show> expected = Set.of(
                new Show(
                        1000L,
                        2000,
                        "third-title",
                        "third-subtitle",
                        "third-description",
                        Category.builder().id(1001L).build(),
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4"
                ),
                new Show(
                        1001L,
                        2001,
                        "second-title",
                        "second-subtitle",
                        "second-description",
                        Category.builder().id(1001L).build(),
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3"
                ),
                new Show(
                        2L,
                        2002,
                        "fourth-title",
                        "fourth-subtitle",
                        "fourth-description",
                        Category.builder().id(1001L).build(),
                        Venue.builder().id(1001L).build(),
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5"
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Show> findAllShows() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                "SELECT id, external_short_id, title, subtitle, description, category_id, venue_id, image_url FROM shows"
        );
    }
}
