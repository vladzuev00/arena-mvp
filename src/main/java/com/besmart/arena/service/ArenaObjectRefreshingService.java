package com.besmart.arena.service;

import com.besmart.arena.client.ArenaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class ArenaObjectRefreshingService {
    private final List<ArenaClient> clients;
    private final List<ArenaObjectRefresher<?, ?, ?, ?, ?, ?, ?>> refreshers;

    public void refresh() {
        clients.stream()
                .map(ArenaClient::request)
                .forEach(this::refresh);
    }

    private void refresh(Object response) {
        refreshers.stream()
                .filter(refresher -> refresher.isSuitableResponse(response))
                //TODO: findFirst
                .forEach(refresher -> refresher.refresh(response));
    }
}
