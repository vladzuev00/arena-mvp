package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Category {
    Long id;
    int externalId;
    String name;
    String primaryColor;
    String secondaryColor;
    Provider provider;
}
