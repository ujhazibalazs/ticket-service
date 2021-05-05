package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.ListRoomsCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ListRoomsCommandHandler {

    private final ListRoomsCommand listRoomsCommand;

    public ListRoomsCommandHandler(ListRoomsCommand listRoomsCommand) {
        this.listRoomsCommand = listRoomsCommand;
    }

    @ShellMethod(value = "Lists all rooms", key = "list rooms")
    public String listRooms() {
        return listRoomsCommand.execute();
    }
}
