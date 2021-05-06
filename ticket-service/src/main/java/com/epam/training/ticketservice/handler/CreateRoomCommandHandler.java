package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateRoomCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class CreateRoomCommandHandler extends AuthenticationProvidingAbstractCommandHandler {

    private final CreateRoomCommand createRoomCommand;

    public CreateRoomCommandHandler(CreateRoomCommand createRoomCommand, Authenticator authenticator) {
        super(authenticator);
        this.createRoomCommand = createRoomCommand;
    }

    @ShellMethod(value = "Creates a new room", key = "create room")
    @ShellMethodAvailability("isAdminSignedIn")
    public String createRoom(String name, int rows, int columns) {
        return createRoomCommand.execute(name, rows, columns);
    }
}
