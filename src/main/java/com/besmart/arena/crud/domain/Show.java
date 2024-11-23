package com.besmart.arena.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class Show {
    Long id;
    int externalShortId;
    String title;
    String subtitle;
    String description;
    Venue venue;
    String imageUrl;
    Promoter promoter;
    List<Category> categories;
    List<Tag> tags;

    //TODO: test
    public int[] getCategoryExternalIds() {
        return categories.stream()
                .mapToInt(Category::getExternalId)
                .toArray();
    }

    //TODO: test
    public String[] getTagNames() {
        return tags.stream()
                .map(Tag::getName)
                .toArray(String[]::new);
    }
}
