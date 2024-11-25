package com.besmart.arena.crud.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.domain.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ProviderRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private ProviderRepository repository;

    @Test
    public void providerShouldBeFoundByName() {
        String givenName = "KAKAVA";

        Provider actual = repository.findByName(givenName);
        Provider expected = new Provider(1L, givenName);
        assertEquals(expected, actual);
    }

    @Test
    public void providerShouldNotBeFoundByName() {
        String givenName = "test-provider";

        assertThrows(EmptyResultDataAccessException.class, () -> repository.findByName(givenName));
    }
}
