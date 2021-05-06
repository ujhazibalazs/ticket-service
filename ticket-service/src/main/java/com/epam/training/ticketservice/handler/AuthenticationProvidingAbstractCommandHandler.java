package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;

public abstract class AuthenticationProvidingAbstractCommandHandler {

    private final Authenticator authenticator;

    public AuthenticationProvidingAbstractCommandHandler(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
