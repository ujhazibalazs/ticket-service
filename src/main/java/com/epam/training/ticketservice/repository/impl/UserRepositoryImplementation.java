package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.domain.Role;
import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImplementation implements UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepositoryImplementation() {
        this.users.add(new User("admin", "admin", Role.ADMINISTRATOR));
    }

    @Override
    public List<User> getAllUser() {
        return users;
    }
}
