package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.RoomEntity;
import com.epam.training.ticketservice.repository.RoomRepository;

import java.util.Optional;

public class DeleteRoomCommand implements Command {

    private final RoomRepository roomRepository;
    private final String roomToDelete;

    public DeleteRoomCommand(RoomRepository roomRepository, String roomToDelete) {
        this.roomRepository = roomRepository;
        this.roomToDelete = roomToDelete;
    }

    @Override
    public String execute() {
        Optional<RoomEntity> foundRoom = roomRepository.findById(roomToDelete);
        if (foundRoom.isEmpty()) {
            return "Room with given name doesn't exist";
        }
        roomRepository.delete(foundRoom.get());
        return "Room successfully deleted";
    }
}
