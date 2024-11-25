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

    public void refreshByName(List<Category> categories) {
        batchUpdate(
                jdbcTemplate,
                categories,
                """
                        INSERT INTO categories(name, primary_color, secondary_color) VALUES(:name, :primaryColor, :secondaryColor)
                        ON CONFLICT (name) DO
                        UPDATE SET name = :name, primary_color = :primaryColor, secondary_color = :secondaryColor WHERE categories.name = :name"""
        );
    }
}
