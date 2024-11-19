package com.besmart.arena.crud.repository;

import com.besmart.arena.crud.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
