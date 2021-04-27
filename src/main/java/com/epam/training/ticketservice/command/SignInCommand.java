package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Role;
import com.epam.training.ticketservice.domain.User;
import com.epam.training.ticketservice.repository.UserRepository;

import java.util.Optional;

public class SignInCommand implements Command{

    private final UserRepository userRepository;
    private final String username;
    private final String password;

    public SignInCommand(UserRepository userRepository, String username, String password) {
        this.userRepository = userRepository;
        this.username = username;
        this.password = password;
    }

    @Override
    public String execute() {
        Optional<User> userToSignIn = userRepository.getAllUser()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        if (userToSignIn.isEmpty()) {
            return "Username doesn't exist.";
        }
        if (!userToSignIn.get().getPassword().equals(password)) {
            return "Wrong password.";
        }
        if (!userToSignIn.get().getRole().equals(Role.ADMINISTRATOR)) {
            return "Permission denied.";
        }
        userToSignIn.get().setSignedIn(true);
        return "Successfully logged in as " + userToSignIn.get().getUsername();
    }
}
