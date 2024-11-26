package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ShowLocationTO {
    String name;

    @JsonCreator
    public ShowLocationTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
