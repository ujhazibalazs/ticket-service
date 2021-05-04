package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.ScreeningEntity;
import com.epam.training.ticketservice.id.ScreeningId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<ScreeningEntity, ScreeningId> {
}
