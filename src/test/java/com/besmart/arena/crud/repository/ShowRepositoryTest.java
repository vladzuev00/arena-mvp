//package com.besmart.arena.crud.repository;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.besmart.arena.crud.domain.*;
//import com.besmart.arena.crud.rowmapper.ShowRowMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
//import static java.util.Collections.emptyList;
//import static java.util.UUID.fromString;
//import static java.util.stream.Collectors.groupingBy;
//import static java.util.stream.Collectors.reducing;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public final class ShowRepositoryTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private ShowRepository repository;
//
//    @Autowired
//    private ShowRowMapper rowMapper;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void showsShouldBeRefreshedByExternalId() {
//        List<Show> givenShows = List.of(
//                Show.builder()
//                        .externalShortId(2000)
//                        .title("third-title")
//                        .subtitle("third-subtitle")
//                        .description("third-description")
//                        .venue(Venue.builder().externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3")).build())
//                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
//                        .promoter(Promoter.builder().externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29938e")).build())
//                        .categories(List.of(Category.builder().externalId(2001).build()))
//                        .tags(emptyList())
//                        .build(),
//                Show.builder()
//                        .externalShortId(2002)
//                        .title("fourth-title")
//                        .subtitle("fourth-subtitle")
//                        .description("fourth-description")
//                        .venue(Venue.builder().externalId(fromString("a60de864-5c52-11ee-a81c-000d3aa868a3")).build())
//                        .imageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
//                        .promoter(Promoter.builder().externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29938e")).build())
//                        .categories(List.of(Category.builder().externalId(2001).build()))
//                        .tags(List.of(Tag.builder().name("first-tag").build(), Tag.builder().name("PROMOTION").build()))
//                        .build()
//        );
//
//        repository.refreshByExternalId(givenShows);
//
//        Set<Show> actual = findAllShows();
//        Set<Show> expected = Set.of(
//
//        );
//        assertEquals(expected, actual);
//    }
//
//    private Set<Show> findAllShows() {
//        try (Stream<Show> stream = jdbcTemplate.queryForStream(
//                "SELECT shows.id, shows.external_short_id, shows.title, shows.subtitle, shows.description, shows.category_id, shows.venue_id, shows.image_url FROM shows "
//                        + "LEFT OUTER JOIN shows_categories ON shows.id = shows_categories.", rowMapper)
//        ) {
//            return stream.collect(
//                            groupingBy(
//                                    Show::getId,
//                                    reducing(
//                                            (result, show) -> new Show(
//                                                    result.getId(),
//                                                    result.getExternalShortId(),
//                                                    result.getTitle(),
//                                                    result.getSubtitle(),
//                                                    result.getDescription(),
//                                                    result.getVenue(),
//                                                    result.getImageUrl(),
//                                                    result.getPromoter(),
//                                                    Stream.concat(result.getCategories().stream(), result.getCategories().stream()).toList(),
//                                                    Stream.concat(result.getTags().stream(), result.getTags().stream()).toList()
//                                            )
//                                    )
//                            )
//                    )
//                    .values().stream().map(Optional::get).collect(Collectors.toSet());
//        }
//
////        return queryForSet(
////                jdbcTemplate,
////                rowMapper,
////                "SELECT id, external_short_id, title, subtitle, description, category_id, venue_id, image_url FROM shows"
////        );
//    }
//}
