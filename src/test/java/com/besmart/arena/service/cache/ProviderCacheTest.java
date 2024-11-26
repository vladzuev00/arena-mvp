package com.besmart.arena.service.cache;

import com.besmart.arena.crud.domain.Provider;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ProviderCacheTest {
    private static final String GIVEN_FIRST_PROVIDER_NAME = "first-provider";
    private static final Provider GIVEN_FIRST_PROVIDER = Provider.builder().build();
    private static final String GIVEN_SECOND_PROVIDER_NAME = "second-provider";
    private static final Provider GIVEN_SECOND_PROVIDER = Provider.builder().build();

    private final ProviderCache cache = new ProviderCache(
            Map.of(
                    GIVEN_FIRST_PROVIDER_NAME, GIVEN_FIRST_PROVIDER,
                    GIVEN_SECOND_PROVIDER_NAME, GIVEN_SECOND_PROVIDER
            )
    );

    @Test
    public void providerShouldBeGotByName() {
        Optional<Provider> optionalActual = cache.get(GIVEN_SECOND_PROVIDER_NAME);
        assertTrue(optionalActual.isPresent());
        Provider actual = optionalActual.get();
        assertSame(GIVEN_SECOND_PROVIDER, actual);
    }

    @Test
    public void providerShouldNotBeGotByName() {
        String givenProviderName = "test-provider";

        Optional<Provider> optionalActual = cache.get(givenProviderName);
        assertTrue(optionalActual.isEmpty());
    }
}
