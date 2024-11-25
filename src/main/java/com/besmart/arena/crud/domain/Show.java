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
    Provider provider;
    List<Category> categories;
    List<Tag> tags;

    public String[] getCategoryNames() {
        return categories.stream()
                .map(Category::getName)
                .toArray(String[]::new);
    }

    public String[] getTagNames() {
        return tags.stream()
                .map(Tag::getName)
                .toArray(String[]::new);
    }
}
