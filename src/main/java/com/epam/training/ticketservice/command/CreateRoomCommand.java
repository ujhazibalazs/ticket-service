package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Room;
import com.epam.training.ticketservice.entity.RoomEntity;
import com.epam.training.ticketservice.repository.RoomRepository;

import java.util.Optional;

public class CreateRoomCommand implements Command {

    private final RoomRepository roomRepository;
    private final Room roomToAdd;

    public CreateRoomCommand(RoomRepository roomRepository, Room roomToAdd) {
        this.roomRepository = roomRepository;
        this.roomToAdd = roomToAdd;
    }

    @Override
    public String execute() {
        Optional<RoomEntity> foundRoom = roomRepository.findById(roomToAdd.getName());
        if (foundRoom.isPresent()) {
            return "Room already exists";
        }
        roomRepository.save(new RoomEntity(roomToAdd.getName(), roomToAdd.getRows(), roomToAdd.getColumns()));
        return "Room successfully created";
    }
}
