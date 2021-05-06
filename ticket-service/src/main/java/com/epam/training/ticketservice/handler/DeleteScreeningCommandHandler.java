package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteScreeningCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class DeleteScreeningCommandHandler {

    private final DeleteScreeningCommand deleteScreeningCommand;
    private final Authenticator authenticator;

    public DeleteScreeningCommandHandler(DeleteScreeningCommand deleteScreeningCommand, Authenticator authenticator) {
        this.deleteScreeningCommand = deleteScreeningCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Deletes an existing screening", key = "delete screening")
    public String deleteScreening(String movieName, String roomName, String startDate) {
        return deleteScreeningCommand.execute(movieName, roomName, startDate);
    }

    @ShellMethodAvailability("delete screening")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
