package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;

@Service
@RequiredArgsConstructor
public final class CategoryRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void refreshByExternalId(List<Category> categories) {
        batchUpdate(
                jdbcTemplate,
                categories,
                """
                        INSERT INTO categories(external_id, name, primary_color, secondary_color, provider_id) VALUES(:externalId, :name, :primaryColor, :secondaryColor, :provider.id)
                        ON CONFLICT (external_id, provider_id) DO
                        UPDATE SET name = :name, primary_color = :primaryColor, secondary_color = :secondaryColor, provider_id = :provider.id
                        WHERE categories.external_id = :externalId AND categories.provider_id = :provider.id"""
        );
    }
}
