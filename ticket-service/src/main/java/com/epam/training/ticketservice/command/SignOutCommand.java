package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.User;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.stereotype.Component;

@Component
public class SignOutCommand implements Command {

    private final Authenticator authenticator;

    public SignOutCommand(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public String execute() {
        User loggedInUser = authenticator.getLoggedInUser();
        if (loggedInUser == null) {
            return "You are not signed in";
        }
        authenticator.setLoggedInUser(null);
        return "Successfully logged out";
    }
}
