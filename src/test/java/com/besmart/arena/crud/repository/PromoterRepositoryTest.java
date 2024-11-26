package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.Promoter;
import com.besmart.arena.crud.rowmapper.PromoterRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static com.besmart.arena.testutil.JdbcTemplateUtil.queryForSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PromoterRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private PromoterRepository repository;

    @Autowired
    private PromoterRowMapper rowMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void promotersShouldBeRefreshedByName() {
        List<Promoter> givenPromoters = List.of(
                Promoter.builder()
                        .name("first-promoter")
                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4")
                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5")
                        .build(),
                Promoter.builder()
                        .name("second-promoter")
                        .iconUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6")
                        .webPageUrl("https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7")
                        .build()
        );

        repository.refreshByExternalId(givenPromoters);

        Set<Promoter> actual = findAllPromoters();
        Set<Promoter> expected = Set.of(
                new Promoter(
                        2L,
                        "second-promoter",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a6",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a7"
                ),
                new Promoter(
                        1001L,
                        "Organizatorius Z, VŠĮ",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5"
                ),
                new Promoter(
                        1000L,
                        "first-promoter",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a4",
                        "https://res.cloudinary.com/kakavalt/image/fetch/w_1024,f_auto,q_auto:best/https://app-kkv-be-test.azurewebsites.net//api/v1/event/picture/95825339-5ec8-11ee-a81c-000d3aa868a5"
                )
        );
        assertEquals(expected, actual);
    }

    private Set<Promoter> findAllPromoters() {
        return queryForSet(
                jdbcTemplate,
                rowMapper,
                """
                        SELECT
                            id AS promoterId,
                            name AS promoterName,
                            icon_url AS promoterIconUrl,
                            web_page_url AS promoterWebPageUrl
                        FROM promoters"""
        );
    }
}
