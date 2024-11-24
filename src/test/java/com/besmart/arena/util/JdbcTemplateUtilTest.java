package com.besmart.arena.util;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.List;

import static com.besmart.arena.util.JdbcTemplateUtil.batchUpdate;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;
import static org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils.createBatch;

public final class JdbcTemplateUtilTest {

    @Test
    public void batchUpdateShouldBeExecuted() {
        try (var mockedSqlParameterSourceUtils = mockStatic(SqlParameterSourceUtils.class)) {
            NamedParameterJdbcTemplate givenJdbcTemplate = mock(NamedParameterJdbcTemplate.class);
            List<?> givenObjects = List.of(mock(Object.class), mock(Object.class));
            String givenQuery = "test-query";

            SqlParameterSource[] givenParameterSources = {
                    mock(SqlParameterSource.class),
                    mock(SqlParameterSource.class)
            };
            mockedSqlParameterSourceUtils.when(() -> createBatch(same(givenObjects))).thenReturn(givenParameterSources);

            batchUpdate(givenJdbcTemplate, givenObjects, givenQuery);

            verify(givenJdbcTemplate, times(1)).batchUpdate(same(givenQuery), same(givenParameterSources));
        }
    }
}
