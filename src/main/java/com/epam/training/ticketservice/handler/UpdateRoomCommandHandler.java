package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.UpdateRoomCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class UpdateRoomCommandHandler {

    private final UpdateRoomCommand updateRoomCommand;
    private final Authenticator authenticator;

    public UpdateRoomCommandHandler(UpdateRoomCommand updateRoomCommand, Authenticator authenticator) {
        this.updateRoomCommand = updateRoomCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Updates a room", key = "update room")
    public String updateRoom(String name, int rows, int columns) {
        return updateRoomCommand.execute(name, rows, columns);
    }

    @ShellMethodAvailability("update room")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
