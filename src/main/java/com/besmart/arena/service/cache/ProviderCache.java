package com.besmart.arena.service.cache;

import com.besmart.arena.crud.domain.Provider;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

//TODO: test
@RequiredArgsConstructor
public final class ProviderCache {
    private final Map<String, Provider> providersByNames;

    public Optional<Provider> get(String name) {
        return ofNullable(providersByNames.get(name));
    }
}
