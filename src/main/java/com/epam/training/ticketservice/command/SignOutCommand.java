package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;

import java.util.Optional;

public class SignOutCommand implements Command {

    private final UserRepository userRepository;

    public SignOutCommand(UserRepository userRepository) {
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
        loggedInUser.get().setSignedIn(false);
        return "Successfully logged out.";
    }
}
