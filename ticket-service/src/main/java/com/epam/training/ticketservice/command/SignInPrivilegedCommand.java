package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Role;
import com.epam.training.ticketservice.entity.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignInPrivilegedCommand {

    private final UserRepository userRepository;
    private final Authenticator authenticator;

    public SignInPrivilegedCommand(UserRepository userRepository, Authenticator authenticator) {
        this.userRepository = userRepository;
        this.authenticator = authenticator;
    }

    public String execute(String username, String password) {
        Optional<User> userToSignIn = userRepository.findById(username);
        if (userToSignIn.isEmpty()) {
            return "Login failed due to incorrect credentials";
        }
        if (!userToSignIn.get().getPassword().equals(password)) {
            return "Login failed due to incorrect credentials";
        }
        if (!userToSignIn.get().getRole().equalsIgnoreCase(Role.ADMINISTRATOR.toString())) {
            return "Permission denied";
        }
        authenticator.setLoggedInUser(userToSignIn.get());
        return "Successfully logged in";
    }
}
