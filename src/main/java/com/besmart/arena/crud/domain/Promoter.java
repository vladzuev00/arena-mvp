package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Promoter {
    Long id;
    String name;
    String iconUrl;
    String webPageUrl;
}
