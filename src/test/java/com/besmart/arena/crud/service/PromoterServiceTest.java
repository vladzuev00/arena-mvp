package com.besmart.arena.crud.service;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.dto.Promoter;
import com.besmart.arena.crud.rowmapper.PromoterRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PromoterServiceTest extends AbstractSpringBootTest {

    @Autowired
    private PromoterService service;

    @Autowired
    private PromoterRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void promotersShouldBeRefreshedByExternalId() {
        List<Promoter> givenPromoters = List.of(
                Promoter.builder()
                        .externalId(fromString("550e8400-e29b-41d4-a716-446655440000"))
                        .name("second-promoter")
                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
                        .build(),
                Promoter.builder()
                        .externalId(fromString("550e8400-e29b-41d4-a716-446655440001"))
                        .name("third-promoter")
                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6")
                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7")
                        .build()
        );

        service.refreshByExternalId(givenPromoters);

        Set<Promoter> actual = findAllPromoters();
        Set<Promoter> expected = Set.of(
                new Promoter(
                        1000L,
                        fromString("550e8400-e29b-41d4-a716-446655440000"),
                        "second-promoter",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5"
                ),
                new Promoter(
                        2L,
                        fromString("550e8400-e29b-41d4-a716-446655440001"),
                        "third-promoter",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7"
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Promoter> findAllPromoters() {
        return queryForSet(jdbcTemplate, rowMapper, "SELECT id, external_id, name, icon_url, web_page_url FROM promoters");
    }
}
