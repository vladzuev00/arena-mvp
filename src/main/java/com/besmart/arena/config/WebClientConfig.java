package com.besmart.arena.config;

import com.besmart.arena.config.property.WebClientProperty;
import com.besmart.arena.service.webclient.observer.WebClientConnectionObserver;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.Connection;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.time.Duration.ofMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

//TODO: refactor and tests
@Configuration
public class WebClientConfig {

    @Bean("web-client")
    public WebClient webClient(WebClientProperty property, WebClientConnectionObserver connectionObserver) {
        ConnectionProvider connectionProvider = createConnectionProvider(property);
        HttpClient httpClient = createHttpClient(connectionProvider, property, connectionObserver);
        ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder().clientConnector(httpConnector).build();
    }

    private ConnectionProvider createConnectionProvider(WebClientProperty property) {
        return ConnectionProvider.builder(property.getConnectionProviderName())
                .maxConnections(property.getMaxConnections())
                .pendingAcquireTimeout(ofMillis(property.getPendingAcquireTimeoutMs()))
                .maxIdleTime(ofMillis(property.getMaxIdleTimeoutMs()))
                .maxLifeTime(ofMillis(property.getMaxLifetimeMs()))
                .build();
    }

    private HttpClient createHttpClient(ConnectionProvider connectionProvider, WebClientProperty property, WebClientConnectionObserver connectionObserver) {
        return HttpClient.create(connectionProvider)
                .doOnConnected(connection -> configureNewConnection(connection, property))
                .option(CONNECT_TIMEOUT_MILLIS, property.getConnectTimeoutMs())
                .observe(connectionObserver);
    }

    private void configureNewConnection(Connection connection, WebClientProperty property) {
        connection
                .addHandlerLast(new ReadTimeoutHandler(property.getReadTimeoutMs(), MILLISECONDS))
                .addHandlerLast(new WriteTimeoutHandler(property.getWriteTimeoutMs(), MILLISECONDS));
    }
}