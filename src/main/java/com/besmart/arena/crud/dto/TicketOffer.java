package com.besmart.arena.crud.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class TicketOffer {
    Long id;
    BigDecimal price;
    String redirectUrl;
    Currency currency;
    Promoter promoter;
    Event event;
}
