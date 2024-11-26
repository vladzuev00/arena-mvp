package com.besmart.arena.service.cache;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public final class ProviderCacheFactory {
    private final ProviderRepository repository;

    public ProviderCache create() {
        try (Stream<Provider> stream = repository.findAll()) {
            return stream.collect(collectingAndThen(toMap(Provider::getName, identity()), ProviderCache::new));
        }
    }
}
