package com.besmart.arena.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Show {
    Long id;
    int externalId;
    String title;
    String subtitle;
    String description;
    Category category;
    Venue venue;
    String imageId;
}
