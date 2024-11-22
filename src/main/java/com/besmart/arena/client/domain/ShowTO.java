package com.besmart.arena.client.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class ShowTO {
    int shortId;
    int eventShortId;
    String eventTitle;
    String eventDescriptionHtml;
    EventPictureTO eventPicture;
    List<CategoryTO> eventCategories;
    LocalDateTime startDateTime;
    List<String> marks;
    ShowLocationTO location;
    String venueAddress;
    int priceFrom;
    PromoterTO promoter;

    @JsonCreator
    public ShowTO(@JsonProperty("shortId") int shortId,
                  @JsonProperty("eventShortId") int eventShortId,
                  @JsonProperty("eventTitle") String eventTitle,
                  @JsonProperty("eventDescription") String eventDescriptionHtml,
                  @JsonProperty("eventPicture") EventPictureTO eventPicture,
                  @JsonProperty("eventCategories") List<CategoryTO> eventCategories,
                  @JsonProperty("startDateTime") LocalDateTime startDateTime,
                  @JsonProperty("marks") List<String> marks,
                  @JsonProperty("location") ShowLocationTO location,
                  @JsonProperty("venueAddress") String venueAddress,
                  @JsonProperty("priceFrom") int priceFrom,
                  @JsonProperty("promoter") PromoterTO promoter) {
        this.shortId = shortId;
        this.eventShortId = eventShortId;
        this.eventTitle = eventTitle;
        this.eventDescriptionHtml = eventDescriptionHtml;
        this.eventPicture = eventPicture;
        this.eventCategories = eventCategories;
        this.startDateTime = startDateTime;
        this.marks = marks;
        this.location = location;
        this.venueAddress = venueAddress;
        this.priceFrom = priceFrom;
        this.promoter = promoter;
    }
}
