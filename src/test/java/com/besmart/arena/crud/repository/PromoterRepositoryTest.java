//package com.besmart.arena.crud.repository;
//
//import com.besmart.arena.base.AbstractSpringBootTest;
//import com.besmart.arena.crud.domain.Promoter;
//import com.besmart.arena.crud.domain.Provider;
//import com.besmart.arena.crud.rowmapper.PromoterRowMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.Set;
//
//import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
//import static java.util.UUID.fromString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public final class PromoterRepositoryTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private PromoterRepository repository;
//
//    @Autowired
//    private PromoterRowMapper rowMapper;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void promotersShouldBeRefreshedByExternalId() {
//        List<Promoter> givenPromoters = List.of(
//                Promoter.builder()
//                        .externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"))
//                        .name("second-promoter")
//                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
//                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
//                        .provider(Provider.builder().id(1L).build())
//                        .build(),
//                Promoter.builder()
//                        .externalId(fromString("11aa329a-44a6-11ed-a81c-000d3a29939e"))
//                        .name("third-promoter")
//                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6")
//                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7")
//                        .provider(Provider.builder().id(2L).build())
//                        .build()
//        );
//
//        repository.refreshByExternalId(givenPromoters);
//
//        Set<Promoter> actual = findAllPromoters();
//        Set<Promoter> expected = Set.of(
//                new Promoter(
//                        1001L,
//                        fromString("11aa329a-44a6-11ed-a81c-000d3a29938e"),
//                        "Organizatorius Z, VŠĮ",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5",
//                        Provider.builder().id(1L).build()
//                ),
//                new Promoter(
//                        2L,
//                        fromString("11aa329a-44a6-11ed-a81c-000d3a29939e"),
//                        "third-promoter",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7",
//                        Provider.builder().id(2L).build()
//                ),
//                new Promoter(
//                        1000L,
//                        fromString("11aa329a-44a6-11ed-a81c-000d3a29937e"),
//                        "second-promoter",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
//                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5",
//                        Provider.builder().id(1L).build()
//                )
//        );
//        assertEquals(expected, actual);
//    }
//
//    private Set<Promoter> findAllPromoters() {
//        return queryForSet(
//                jdbcTemplate,
//                rowMapper,
//                """
//                        SELECT
//                            id AS promoterId,
//                            external_id AS promoterExternalId,
//                            name AS promoterName,
//                            icon_url AS promoterIconUrl,
//                            web_page_url AS promoterWebPageUrl,
//                            provider_id AS promoterProviderId
//                        FROM promoters"""
//        );
//    }
//}
