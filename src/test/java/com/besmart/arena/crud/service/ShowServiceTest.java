package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Category;
import com.besmart.arena.crud.dto.Show;
import com.besmart.arena.crud.dto.Venue;
import com.besmart.arena.crud.rowmapper.ShowRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

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
                        .externalShortId(255)
                        .title("third-title")
                        .subtitle("third-subtitle")
                        .description("second-description")
                        .category(Category.builder().externalId(2555).build())
                        .venue(Venue.builder().externalId(fromString("550e8400-e29b-41d4-a717-446655440000")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2")
                        .build(),
                Show.builder()
                        .externalShortId(256)
                        .title("third-title")
                        .subtitle("third-subtitle")
                        .description("third-description")
                        .category(Category.builder().externalId(2555).build())
                        .venue(Venue.builder().externalId(fromString("550e8400-e29b-41d4-a717-446655440000")).build())
                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3")
                        .build()
        );

        service.refreshByExternalId(givenShows);

        Set<Show> actual = findAllShows();
        //[Show(id=1, externalShortId=255, title=second-title, subtitle=second-subtitle, description=second-description, category=Category(id=129, externalId=0, name=null, primaryColor=null, secondaryColor=null), venue=Venue(id=132, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a2),
        // Show(id=2, externalShortId=256, title=third-title, subtitle=third-subtitle, description=third-description, category=Category(id=129, externalId=0, name=null, primaryColor=null, secondaryColor=null), venue=Venue(id=132, externalId=null, name=null, address=null, latitude=0.0, longitude=0.0), imageUrl=https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a3)]
        Set<Show> expected = Set.of(

        );
        assertEquals(expected, actual);
    }

    private Set<Show> findAllShows() {
        try (var stream = jdbcTemplate.queryForStream("SELECT id, external_short_id, title, subtitle, description, category_id, venue_id, image_url FROM shows", rowMapper)) {
            return stream.collect(toUnmodifiableSet());
        }
    }
}
