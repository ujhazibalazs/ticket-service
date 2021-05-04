package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteRoomCommand;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DeleteRoomCommandHandler {

    private final RoomRepository roomRepository;

    public DeleteRoomCommandHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @ShellMethod(value = "Deletes a room", key = "delete room")
    private String deleteRoom(String name) {
        DeleteRoomCommand command = new DeleteRoomCommand(roomRepository, name);
        return command.execute();
    }
}
