package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.Promoter;

public interface PromoterMapper<TO> {
    Promoter map(TO to);
}
