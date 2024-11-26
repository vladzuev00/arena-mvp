package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Category {
    Long id;
    String externalId;
    String name;
    boolean hidden;
    Provider provider;
}
