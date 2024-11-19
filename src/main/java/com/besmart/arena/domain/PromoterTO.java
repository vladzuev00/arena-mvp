package com.besmart.arena.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class PromoterTO {
    UUID id;
    String name;
    String address;
    String email;
    String phoneNumber;
    String pictureUrl;

    @JsonCreator
    public PromoterTO(@JsonProperty("id") UUID id,
                      @JsonProperty("name") String name,
                      @JsonProperty("address") String address,
                      @JsonProperty("email") String email,
                      @JsonProperty("phone") String phoneNumber,
                      @JsonProperty("pictureUrl") String pictureUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pictureUrl = pictureUrl;
    }
}
