package com.besmart.arena.client.domain;

import com.besmart.arena.domain.ShowTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class KakavaShowsResponseTO {
    List<ShowTO> shows;

    @JsonCreator
    public KakavaShowsResponseTO(@JsonProperty("shows") List<ShowTO> shows) {
        this.shows = shows;
    }
}
