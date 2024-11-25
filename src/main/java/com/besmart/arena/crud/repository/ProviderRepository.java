package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.rowmapper.ProviderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public final class ProviderRepository {
    private static final String PARAMETER_NAME_NAME = "name";
    
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ProviderRowMapper rowMapper;

    public Provider findByName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT providers.id AS providerId, providers.name AS providerName FROM providers WHERE providers.name = :name",
                Map.of(PARAMETER_NAME_NAME, name),
                rowMapper
        );
    }
}
