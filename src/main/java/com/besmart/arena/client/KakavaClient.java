package com.besmart.arena.client;

import com.besmart.arena.client.domain.*;
import com.besmart.arena.config.property.KakavaClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.web.reactive.function.BodyInserters.fromFormData;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Component
@RequiredArgsConstructor
public final class KakavaClient {
    private static final String CLIENT_ID_PARAM_NAME = "client_id";
    private static final String GRANT_TYPE_PARAM_NAME = "grant_type";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String USERNAME_PARAM_NAME = "username";
    private static final String VENUES_PARAM_NAME = "venues";
    private static final String VENUE_PARAM_NAME = "venue";

    private final WebClient webClient;
    private final KakavaClientProperty property;

//    public AuthResponseTO auth() {
//        return webClient.post()
//                .uri(buildAuthUri())
//                .header(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
//                .body(getAuthBody())
//                .retrieve()
//                .bodyToMono(AuthResponseTO.class)
//                .block();
//    }

    public ShowsResponseTO getShows(String language, List<UUID> venueIds) {
        return webClient.get()
                .uri(buildGettingShowsUri(venueIds))
                .header(ACCEPT_LANGUAGE, language)
                .retrieve()
                .bodyToMono(ShowsResponseTO.class)
                .block();
    }

//    public GettingOrdersResponseTO getOrders(String accessToken, UUID venueId) {
//        return webClient.get()
//                .uri(buildGettingOrdersUri(venueId))
//                .header(AUTHORIZATION, buildAuthHeaderValue(accessToken))
//                .retrieve()
//                .bodyToMono(GettingOrdersResponseTO.class)
//                .block();
//    }
//
//    public UsingTicketResponseTO useTicket(String accessToken, UsingTicketBodyTO body) {
//        return webClient.post()
//                .uri(buildUsingTicketUrl())
//                .header(AUTHORIZATION, buildAuthHeaderValue(accessToken))
//                .bodyValue(body)
//                .retrieve()
//                .bodyToMono(UsingTicketResponseTO.class)
//                .block();
//    }

    private String buildAuthUri() {
        return fromHttpUrl(property.getBaseUrl())
                .path(property.getAuthPath())
                .toUriString();
    }

    private String buildGettingShowsUri(List<UUID> venueIds) {
        return fromHttpUrl(property.getBaseUrl())
                .path(property.getShowsPath())
                .queryParam(VENUES_PARAM_NAME, venueIds)
                .toUriString();
    }

    private String buildGettingOrdersUri(UUID venueId) {
        return fromHttpUrl(property.getBaseUrl())
                .path(property.getTicketsPath())
                .queryParam(VENUE_PARAM_NAME, venueId)
                .toUriString();
    }

    private String buildUsingTicketUrl() {
        return fromHttpUrl(property.getBaseUrl())
                .path(property.getUsingTicketPath())
                .toUriString();
    }

    private BodyInserter<?, ? super ClientHttpRequest> getAuthBody() {
        return fromFormData(CLIENT_ID_PARAM_NAME, property.getClientId().toString())
                .with(GRANT_TYPE_PARAM_NAME, property.getGrantType())
                .with(PASSWORD_PARAM_NAME, property.getPassword())
                .with(USERNAME_PARAM_NAME, property.getUsername());
    }

    private String buildAuthHeaderValue(String accessToken) {
        return "Bearer " + accessToken;
    }
}
