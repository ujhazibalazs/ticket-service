package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ListRoomsCommand implements Command {

    private final RoomRepository roomRepository;

    public ListRoomsCommand(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public String execute() {
        String rooms = roomRepository.findAll()
                .stream()
                .map(roomEntity -> "Room "
                        + roomEntity.getName() + " with "
                        + roomEntity.getNumberOfSeats() + " seats, "
                        + roomEntity.getRows() + " rows and " + roomEntity.getColumns() + " columns")
                .collect(Collectors.joining(System.lineSeparator()));
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        return rooms;
    }
}
