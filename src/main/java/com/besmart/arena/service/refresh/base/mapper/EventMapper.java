package com.besmart.arena.service.refresh.base.mapper;

import com.besmart.arena.crud.domain.Event;
import com.besmart.arena.crud.domain.Provider;

public interface EventMapper<TO> {
    Event map(TO to, Provider provider);
}
