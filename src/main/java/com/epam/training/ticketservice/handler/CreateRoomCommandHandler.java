package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateRoomCommand;
import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CreateRoomCommandHandler {

    private final RoomRepository roomRepository;

    public CreateRoomCommandHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @ShellMethod(value = "Creates a new room", key = "create room")
    public String createRoom(String name, int rows, int columns) {
        CreateRoomCommand command = new CreateRoomCommand(roomRepository, new Room(name, rows, columns));
        return command.execute();
    }
}
