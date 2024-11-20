package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ShowMarkTO {
    String name;

    @JsonCreator
    public ShowMarkTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
