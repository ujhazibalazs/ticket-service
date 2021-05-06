package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateScreeningCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class CreateScreeningCommandHandler {

    private final CreateScreeningCommand createScreeningCommand;
    private final Authenticator authenticator;

    public CreateScreeningCommandHandler(CreateScreeningCommand createScreeningCommand, Authenticator authenticator) {
        this.createScreeningCommand = createScreeningCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Creates a new screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String startDate) {
        return createScreeningCommand.execute(movieName, roomName, startDate);
    }

    @ShellMethodAvailability("create screening")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
