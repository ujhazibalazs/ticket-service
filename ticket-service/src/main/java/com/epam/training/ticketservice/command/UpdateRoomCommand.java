package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateRoomCommand {

    private final RoomRepository roomRepository;

    public UpdateRoomCommand(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public String execute(String name, int rows, int columns) {
        Room updatedRoom = new Room(name, rows, columns);
        Optional<Room> roomToUpdate = roomRepository.findById(updatedRoom.getName());
        if (roomToUpdate.isEmpty()) {
            return "Room with given name doesn't exist";
        }
        roomRepository.save(updatedRoom);
        return "Room successfully updated";
    }
}
