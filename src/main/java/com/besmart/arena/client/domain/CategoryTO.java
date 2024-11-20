package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CategoryTO {
    int id;

    @JsonCreator
    public CategoryTO(@JsonProperty("id") int id) {
        this.id = id;
    }
}
