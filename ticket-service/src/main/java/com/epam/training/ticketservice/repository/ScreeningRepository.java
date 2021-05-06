package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.id.ScreeningId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, ScreeningId> {
    List<Screening> findAllByOrderByCreateDate();
}
