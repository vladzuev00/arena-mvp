package com.besmart.arena.service.cache;

import com.besmart.arena.crud.domain.Venue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class VenueCache {
    private final Venue venue;
}
