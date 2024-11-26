package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PromoterTO {
    String name;

    @JsonCreator
    public PromoterTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
