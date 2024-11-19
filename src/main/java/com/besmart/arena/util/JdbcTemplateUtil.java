package com.besmart.arena.util;

import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

import static org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils.createBatch;

@UtilityClass
public final class JdbcTemplateUtil {

    public static void batchUpdate(NamedParameterJdbcTemplate jdbcTemplate, List<?> objects, String query) {
        SqlParameterSource[] parameterSources = createBatch(objects);
        jdbcTemplate.batchUpdate(query, parameterSources);
    }
}
