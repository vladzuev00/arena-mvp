package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Provider {
    Long id;
    String name;
}
