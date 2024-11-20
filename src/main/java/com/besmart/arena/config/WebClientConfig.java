package com.besmart.arena.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.Connection;
import reactor.netty.ConnectionObserver;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class WebClientConfig implements ConnectionObserver {

    @Bean("web-client")
    public WebClient webClientInternal() {
        return getWebClientBuilder().build();
    }

    public WebClient.Builder getWebClientBuilder() {
        ConnectionProvider connectionProvider =
                ConnectionProvider.builder("aod-http-client")
                        .maxConnections(5)
                        .pendingAcquireTimeout(Duration.ofMillis(100000))
                        .maxIdleTime(Duration.ofMillis(100000))
                        .maxLifeTime(Duration.ofMillis(100000))
                        .build();
        HttpClient httpClient = HttpClient.create(connectionProvider)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(
                                100000,
                                TimeUnit.MILLISECONDS
                        ))
                        .addHandlerLast(new WriteTimeoutHandler(
                                100000,
                                TimeUnit.MILLISECONDS
                        ))
                ).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100000)
                .observe(this);

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
    }

    /**
     * @see ConnectionObserver#onStateChange(Connection, State)
     */
    @Override
    public void onStateChange(
            @NotNull Connection connection,
            @NotNull State newState
    ) {
        log.info("WebClient State Change: connection={}, newState={}", connection, newState);
    }

    /**
     * @see ConnectionObserver#onUncaughtException(Connection, Throwable)
     */
    @Override
    public void onUncaughtException(
            @NotNull Connection connection,
            @NotNull Throwable error
    ) {
        log.error("WebClient Uncaught Exception: connection={}", connection, error);
    }
}