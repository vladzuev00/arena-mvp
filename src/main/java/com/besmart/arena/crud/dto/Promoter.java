package com.besmart.arena.crud.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class Promoter {
    Long id;
    UUID externalId;
    String name;
    String iconUrl;
    String webPageUrl;
}
