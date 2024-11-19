package com.besmart.arena.crud.dto;

import lombok.Value;

@Value
public class Category {
    Long id;
    int externalId;
    String name;
    String primaryColor;
    String secondaryColor;
}
