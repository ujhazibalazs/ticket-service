package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {
}
