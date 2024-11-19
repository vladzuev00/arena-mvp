package com.besmart.arena.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Venue {
    Long id;
    String name;
    String address;
    double latitude;
    double longitude;
}
