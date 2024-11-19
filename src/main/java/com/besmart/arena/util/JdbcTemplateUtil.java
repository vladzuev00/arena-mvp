package com.besmart.arena.util;

import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils.createBatch;

@UtilityClass
public final class JdbcTemplateUtil {

    public static void batchUpdate(NamedParameterJdbcTemplate jdbcTemplate, List<?> objects, String query) {
        jdbcTemplate.batchUpdate(query, createBatch(objects));
    }
}
