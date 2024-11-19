package com.besmart.arena.repository;

import com.besmart.arena.base.AbstractSpringBootTest;
import com.besmart.arena.crud.entity.EventEntity;
import com.besmart.arena.crud.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class EventRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private EventRepository repository;

    @Test
    @Sql(statements = "INSERT INTO events(id) VALUES(255)")
    public void tickerShouldBeFoundById() {
        Optional<EventEntity> optionalActual = repository.findById(255L);
        assertTrue(optionalActual.isPresent());
    }
}
