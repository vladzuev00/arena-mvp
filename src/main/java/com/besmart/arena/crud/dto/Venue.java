package com.besmart.arena.crud.dto;

import lombok.Value;

@Value
public class Venue {
    Integer id;
    String name;
    String address;
    double latitude;
    double longitude;
}
