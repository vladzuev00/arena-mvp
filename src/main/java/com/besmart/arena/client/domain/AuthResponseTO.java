package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AuthResponseTO {
    String accessToken;

    @JsonCreator
    public AuthResponseTO(@JsonProperty("access_token") String accessToken) {
        this.accessToken = accessToken;
    }
}
