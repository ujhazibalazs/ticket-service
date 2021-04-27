package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.domain.Role;
import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImplementation implements UserRepository {

    private List<User> users;

    public UserRepositoryImplementation() {
        this.users = List.of(new User("admin", "admin", Role.ADMINISTRATOR));
    }

    @Override
    public List<User> getAllUser() {
        return users;
    }
}
