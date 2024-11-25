package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Promoter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class PromoterRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByName(List<Promoter> promoters) {
        batchUpdate(
                jdbcTemplate,
                promoters,
                """
                        INSERT INTO promoters(name, icon_url, web_page_url) VALUES(:name, :iconUrl, :webPageUrl)
                        ON CONFLICT (name) DO
                        UPDATE SET name = :name, icon_url = :iconUrl, web_page_url = :webPageUrl WHERE promoters.name = :name"""
        );
    }
}
