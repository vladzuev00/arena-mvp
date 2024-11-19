package com.besmart.arena.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class Promoter {
    Long id;
    UUID externalId;
    String name;
    String iconUrl;
    String webPageUrl;
}
