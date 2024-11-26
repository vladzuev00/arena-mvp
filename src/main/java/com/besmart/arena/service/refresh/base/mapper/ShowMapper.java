package com.besmart.arena.service.refresh.base.mapper;

import com.besmart.arena.crud.domain.Provider;
import com.besmart.arena.crud.domain.Show;
import com.besmart.arena.crud.domain.Venue;

public interface ShowMapper<TO> {
    Show map(TO to, Provider provider, Venue venue);
}
