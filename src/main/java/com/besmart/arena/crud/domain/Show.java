package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class Show {
    Long id;
    String externalId;
    String name;
    String description;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    String youtubeUrl;
    String discount;
    String imageUrl;
    SaleStatus saleStatus;
    String duration;
    String tags;
    int priceFrom;
    int priceFromWithTaxes;
    boolean clubTicketsAvailable;
    Venue venue;
    Promoter promoter;
    Provider provider;
    List<Category> categories;

    public String[] getCategoryNames() {
        return categories.stream()
                .map(Category::getName)
                .toArray(String[]::new);
    }
}
