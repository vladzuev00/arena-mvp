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

    public void refreshByExternalId(List<Promoter> promoters) {
        batchUpdate(
                jdbcTemplate,
                promoters,
                """
                        INSERT INTO promoters(external_id, name, icon_url, web_page_url, provider_id) VALUES(:externalId, :name, :iconUrl, :webPageUrl, :provider.id)
                        ON CONFLICT (external_id, provider_id) DO
                        UPDATE SET name = :name, icon_url = :iconUrl, web_page_url = :webPageUrl, provider_id = :provider.id
                        WHERE promoters.external_id = :externalId AND promoters.provider_id = :provider.id"""
        );
    }
}
