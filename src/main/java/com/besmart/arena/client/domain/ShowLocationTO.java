package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class ShowLocationTO {
    UUID id;
    String name;
    String address;

    @JsonCreator
    public ShowLocationTO(@JsonProperty("id") UUID id,
                          @JsonProperty("name") String name,
                          @JsonProperty("address") String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
