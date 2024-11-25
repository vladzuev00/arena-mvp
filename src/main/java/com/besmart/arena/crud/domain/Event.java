package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class Event {
    Long id;
    String externalShortId;
    String title;
    String subtitle;
    String description;
    LocalDateTime dateTime;
    Show show;
    Provider provider;
}
