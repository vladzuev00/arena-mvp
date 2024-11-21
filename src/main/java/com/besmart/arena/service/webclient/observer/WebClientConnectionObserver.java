package com.besmart.arena.service.webclient.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.netty.Connection;
import reactor.netty.ConnectionObserver;

//TODO: refactor and tests
@Slf4j
@Component
public final class WebClientConnectionObserver implements ConnectionObserver {

    @Override
    @SuppressWarnings("NullableProblems")
    public void onStateChange(Connection connection, State newState) {
        log.info("Web client's state was changed: connection={}, newState={}", connection, newState);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void onUncaughtException(Connection connection, Throwable exception) {
        log.error("Uncaught exception was arisen: connection={}", connection, exception);
    }
}
