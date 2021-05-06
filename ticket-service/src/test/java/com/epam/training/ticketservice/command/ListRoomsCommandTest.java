package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ListRoomsCommandTest {

    private ListRoomsCommand underTest;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ListRoomsCommand(roomRepository);
    }

    @Test
    public void testExecuteShouldReturnThereAreNoRoomsAtTheMomentWhenRoomNotFound() {
        // Given
        given(roomRepository.findAll()).willReturn(Collections.emptyList());

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("There are no rooms at the moment"));
    }

    @Test
    public void testExecuteShouldReturnAllRoomsWhenRoomsFound() {
        // Given
        Room room = Mockito.mock(Room.class);

        String roomName = "roomName";
        int rows = 2;
        int columns = 4;
        int numberOfSeats = rows * columns;

        given(roomRepository.findAll()).willReturn(List.of(room));
        given(room.getName()).willReturn(roomName);
        given(room.getNumberOfSeats()).willReturn(numberOfSeats);
        given(room.getRows()).willReturn(rows);
        given(room.getColumns()).willReturn(columns);

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("Room "
                + roomName
                + " with "
                + numberOfSeats
                + " seats, "
                + rows
                + " rows and "
                + columns
                + " columns"));
    }
}