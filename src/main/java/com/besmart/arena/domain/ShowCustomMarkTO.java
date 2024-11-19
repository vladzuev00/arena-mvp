package com.besmart.arena.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ShowCustomMarkTO {
    String name;
    String color; //TODO: not used
    String pictureUrl; //TODO: not used

    @JsonCreator
    public ShowCustomMarkTO(@JsonProperty("name") String name,
                            @JsonProperty("color") String color,
                            @JsonProperty("pictureUrl") String pictureUrl) {
        this.name = name;
        this.color = color;
        this.pictureUrl = pictureUrl;
    }
}
