package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.Venue;

public interface VenueMapper<TO> {
    Venue map(TO to);
}
