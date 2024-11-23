package com.besmart.arena.service;

import com.besmart.arena.service.refresh.ArenaObjectRefresher;
import com.besmart.arena.service.refresh.ArenaObjectRefreshingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public final class ArenaObjectRefreshingServiceTest {

    @Mock
    private ArenaObjectRefresher<?, ?, ?, ?, ?, ?, ?> firstMockedRefresher;

    @Mock
    private ArenaObjectRefresher<?, ?, ?, ?, ?, ?, ?> secondMockedRefresher;

    @Mock
    private ArenaObjectRefresher<?, ?, ?, ?, ?, ?, ?> thirdMockedRefresher;

    private ArenaObjectRefreshingService service;

    @BeforeEach
    public void initializeService() {
        service = new ArenaObjectRefreshingService(
                List.of(firstMockedRefresher, secondMockedRefresher, thirdMockedRefresher)
        );
    }

    @Test
    public void objectsShouldBeRefreshed() {
        service.refresh();

        verify(firstMockedRefresher, times(1)).refresh();
        verify(secondMockedRefresher, times(1)).refresh();
        verify(thirdMockedRefresher, times(1)).refresh();
    }
}
