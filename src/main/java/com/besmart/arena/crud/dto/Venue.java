package com.besmart.arena.crud.dto;

import lombok.*;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class Venue {
    Long id;
    UUID externalId;
    String name;
    String address;
    double latitude;
    double longitude;
}
