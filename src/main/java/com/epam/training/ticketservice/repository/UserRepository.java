package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
