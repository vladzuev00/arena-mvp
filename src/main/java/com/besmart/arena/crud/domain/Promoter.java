package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Promoter {
    Long id;
    String externalId;
    String name;
    String address;
    String email;
    String phone;
    String imageUrl;
    String externalUrl;
    Provider provider;
}
