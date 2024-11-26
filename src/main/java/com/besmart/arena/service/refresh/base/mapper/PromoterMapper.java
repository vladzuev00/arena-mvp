package com.besmart.arena.service.refresh.base.mapper;

import com.besmart.arena.crud.domain.Promoter;
import com.besmart.arena.crud.domain.Provider;

public interface PromoterMapper<TO> {
    Promoter map(TO to, Provider provider);
}
