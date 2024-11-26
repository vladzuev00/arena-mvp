package com.besmart.arena.service.refresh;

import com.besmart.arena.service.refresh.base.ArenaObjectRefresher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class ArenaObjectRefreshingService {
    private final List<ArenaObjectRefresher<?, ?, ?, ?, ?>> refreshers;

    public void refresh() {
        refreshers.forEach(ArenaObjectRefresher::refresh);
    }
}
