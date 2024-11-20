package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EventPictureTO {
    String desktopPictureUrl;

    @JsonCreator
    public EventPictureTO(@JsonProperty("desktopPictureUrl") String desktopPictureUrl) {
        this.desktopPictureUrl = desktopPictureUrl;
    }
}
