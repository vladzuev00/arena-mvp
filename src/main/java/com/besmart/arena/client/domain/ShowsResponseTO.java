package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class ShowsResponseTO {
    List<ShowTO> shows;

    @JsonCreator
    public ShowsResponseTO(@JsonProperty("shows") List<ShowTO> shows) {
        this.shows = shows;
    }
}
