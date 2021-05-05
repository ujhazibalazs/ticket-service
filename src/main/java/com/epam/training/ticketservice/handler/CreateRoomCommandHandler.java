package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateRoomCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class CreateRoomCommandHandler {

    private final CreateRoomCommand createRoomCommand;
    private final Authenticator authenticator;

    public CreateRoomCommandHandler(CreateRoomCommand createRoomCommand, Authenticator authenticator) {
        this.createRoomCommand = createRoomCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Creates a new room", key = "create room")
    public String createRoom(String name, int rows, int columns) {
        return createRoomCommand.execute(name, rows, columns);
    }

    @ShellMethodAvailability("create room")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
