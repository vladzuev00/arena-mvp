package com.besmart.arena.service.refresh.base.mapper;

import com.besmart.arena.crud.domain.Category;
import com.besmart.arena.crud.domain.Provider;

public interface CategoryMapper<TO> {
    Category map(TO to, Provider provider);
}
