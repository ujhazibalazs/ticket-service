package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.User;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.stereotype.Component;

@Component
public class DescribeAccountCommand implements Command {

    private final Authenticator authenticator;

    public DescribeAccountCommand(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public String execute() {
        User loggedInUser = authenticator.getLoggedInUser();
        if (loggedInUser == null) {
            return "You are not signed in";
        }
        return "Signed in with privileged account '" + loggedInUser.getUsername() + "'";
    }
}
