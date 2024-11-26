package com.besmart.arena.service.cache;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.repository.ProviderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.stream.Stream;

import static com.besmart.arena.testutil.ReflectionUtil.getFieldValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public final class ProviderCacheFactoryTest {
    private static final String FIELD_NAME_PROVIDERS_BY_NAMES = "providersByNames";

    @Mock
    private ProviderRepository mockedRepository;

    private boolean streamClosed;

    private ProviderCacheFactory factory;

    @BeforeEach
    public void initializeFactory() {
        factory = new ProviderCacheFactory(mockedRepository);
    }

    @AfterEach
    public void clearStreamClosed() {
        streamClosed = false;
    }

    @Test
    public void cacheShouldBeCreated() {
        Provider firstGivenProvider = new Provider(255L, "first-provider");
        Provider secondGivenProvider = new Provider(256L, "second-provider");
        var givenStream = Stream.of(firstGivenProvider, secondGivenProvider).onClose(() -> streamClosed = true);
        when(mockedRepository.findAll()).thenReturn(givenStream);

        ProviderCache actual = factory.create();
        Map<String, Provider> actualProvidersByNames = getProvidersByNames(actual);
        Map<String, Provider> expectedProvidersByNames = Map.of(
                firstGivenProvider.getName(), firstGivenProvider,
                secondGivenProvider.getName(), secondGivenProvider
        );
        assertEquals(expectedProvidersByNames, actualProvidersByNames);

        assertTrue(streamClosed);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Provider> getProvidersByNames(ProviderCache cache) {
        return getFieldValue(cache, FIELD_NAME_PROVIDERS_BY_NAMES, Map.class);
    }
}
