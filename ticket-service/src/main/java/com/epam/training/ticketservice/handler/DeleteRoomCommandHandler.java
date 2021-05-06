package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteRoomCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class DeleteRoomCommandHandler {

    private final DeleteRoomCommand deleteRoomCommand;
    private final Authenticator authenticator;

    public DeleteRoomCommandHandler(DeleteRoomCommand deleteRoomCommand, Authenticator authenticator) {
        this.deleteRoomCommand = deleteRoomCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Deletes a room", key = "delete room")
    private String deleteRoom(String name) {
        return deleteRoomCommand.execute(name);
    }

    @ShellMethodAvailability("delete room")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
