package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;

import java.util.Optional;

public class DescribeAccountCommand implements Command {

    private final UserRepository userRepository;

    public DescribeAccountCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute() {
        Optional<User> loggedInUser = userRepository.getAllUser()
                .stream()
                .filter(User::isSignedIn)
                .findFirst();
        if (loggedInUser.isEmpty()) {
            return "You are not signed in";
        }
        return "Signed in with privileged account " + loggedInUser.get().getUsername();
    }
}
