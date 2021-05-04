package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.UpdateRoomCommand;
import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UpdateRoomCommandHandler {

    private final RoomRepository roomRepository;

    public UpdateRoomCommandHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @ShellMethod(value = "Updates a room", key = "update room")
    public String updateRoom(String name, int rows, int columns) {
        UpdateRoomCommand command = new UpdateRoomCommand(roomRepository, new Room(name, rows, columns));
        return command.execute();
    }
}
