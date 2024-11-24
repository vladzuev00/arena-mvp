package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
public class ShowLocationTO {
    UUID id;
    String name;

    @Builder
    @JsonCreator
    public ShowLocationTO(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
