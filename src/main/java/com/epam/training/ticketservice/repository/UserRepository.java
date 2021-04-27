package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUser();
}
