package com.besmart.arena.crud.service;

import com.besmart.arena.crud.dto.Promoter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class PromoterService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Promoter> promoters) {
        batchUpdate(
                jdbcTemplate,
                promoters,
                """
                        INSERT INTO promoters(external_id, name, icon_url, web_page_url) VALUES(:externalId, :name, :iconUrl, :webPageUrl)
                        ON CONFLICT (external_id) DO
                        UPDATE SET name = :name, icon_url = :iconUrl, web_page_url = :webPageUrl WHERE promoters.external_id = :externalId"""
        );
    }
}
