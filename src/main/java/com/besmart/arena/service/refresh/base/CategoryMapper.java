package com.besmart.arena.service.refresh.base;

import com.besmart.arena.crud.domain.Category;

public interface CategoryMapper<TO> {
    Category map(TO to);
}
