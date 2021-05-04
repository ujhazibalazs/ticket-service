package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.entity.RoomEntity;
import com.epam.training.ticketservice.repository.RoomRepository;

import java.util.Optional;

public class UpdateRoomCommand implements Command {

    private final RoomRepository roomRepository;
    private final Room roomToUpdate;

    public UpdateRoomCommand(RoomRepository roomRepository, Room roomToUpdate) {
        this.roomRepository = roomRepository;
        this.roomToUpdate = roomToUpdate;
    }

    @Override
    public String execute() {
        Optional<RoomEntity> foundRoom = roomRepository.findById(roomToUpdate.getName());
        if (foundRoom.isEmpty()) {
            return "Room with given name doesn't exist";
        }
        roomRepository.save(new RoomEntity(roomToUpdate.getName(), roomToUpdate.getRows(), roomToUpdate.getColumns()));
        return "Room successfully updated";
    }
}
