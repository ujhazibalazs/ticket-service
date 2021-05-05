package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteRoomCommand {

    private final RoomRepository roomRepository;

    public DeleteRoomCommand(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public String execute(String roomToDelete) {
        Optional<Room> foundRoom = roomRepository.findById(roomToDelete);
        if (foundRoom.isEmpty()) {
            return "Room with given name doesn't exist";
        }
        roomRepository.delete(foundRoom.get());
        return "Room successfully deleted";
    }
}
