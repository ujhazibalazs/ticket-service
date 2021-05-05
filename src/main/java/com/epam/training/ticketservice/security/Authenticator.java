package com.epam.training.ticketservice.security;

import com.epam.training.ticketservice.domain.Role;
import com.epam.training.ticketservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Authenticator {
    private User loggedInUser;

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public boolean isAdmin() {
        if (!isUserLoggedIn()) {
            return false;
        }
        return loggedInUser.getRole().equalsIgnoreCase(Role.ADMINISTRATOR.toString());
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
