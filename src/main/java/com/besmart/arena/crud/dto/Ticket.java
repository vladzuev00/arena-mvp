package com.besmart.arena.crud.dto;

import lombok.Value;

@Value
public class Ticket {
    Long id;
    TicketType type;
    TicketStatus status;
    String sector;
    String row;
    String seat;
    String clientEmail;
    String clientId;
    TicketOffer offer;
}
