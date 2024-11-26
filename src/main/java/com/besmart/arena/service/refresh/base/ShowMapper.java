package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.Show;

public interface ShowMapper<TO> {
    Show map(TO to);
}
