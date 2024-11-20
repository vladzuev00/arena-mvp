package com.besmart.arena.testutil;

import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableSet;

@UtilityClass
public final class JdbcTemplateUtil {

    public static <T> Set<T> queryForSet(JdbcTemplate jdbcTemplate, RowMapper<T> rowMapper, String query) {
        try (Stream<T> stream = jdbcTemplate.queryForStream(query, rowMapper)) {
            return stream.collect(toUnmodifiableSet());
        }
    }
}
