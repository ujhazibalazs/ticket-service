package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateRoomCommand {

    private final RoomRepository roomRepository;

    public CreateRoomCommand(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public String execute(String name, int rows, int columns) {
        Room roomToAdd = new Room(name, rows, columns);
        Optional<Room> foundRoom = roomRepository.findById(roomToAdd.getName());
        if (foundRoom.isPresent()) {
            return "Room already exists";
        }
        roomRepository.save(new Room(roomToAdd.getName(), roomToAdd.getRows(), roomToAdd.getColumns()));
        return "Room successfully created";
    }
}
