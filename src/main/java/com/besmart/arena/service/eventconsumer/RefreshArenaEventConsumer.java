package com.besmart.arena.service.eventconsumer;

import com.besmart.arena.event.RefreshArenaEvent;
import com.besmart.arena.service.refresh.ArenaObjectRefreshingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//TODO: refactor and test
@Component
@RequiredArgsConstructor
public final class RefreshArenaEventConsumer {
    private final ArenaObjectRefreshingService service;

    @KafkaListener(topics = "arena-events", groupId = "arena-event-group", concurrency = "1")
    public void consume(RefreshArenaEvent event) {
        service.refresh();
    }
}
