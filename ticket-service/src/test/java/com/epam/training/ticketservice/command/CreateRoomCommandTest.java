package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CreateRoomCommandTest {

    private static final String NAME = "name";
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;
    private CreateRoomCommand underTest;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CreateRoomCommand(roomRepository);
    }

    @Test
    public void testExecuteShouldReturnRoomAlreadyExistsWhenRoomFound() {
        // Given
        Room room = Mockito.mock(Room.class);
        given(roomRepository.findById(anyString())).willReturn(Optional.of(room));

        // When
        String result = underTest.execute(NAME, ROWS, COLUMNS);

        // Then
        assertThat(result, equalTo("Room already exists"));
    }

    @Test
    public void testExecuteShouldReturnRoomSuccessfullyCreatedWhenRoomNotFound() {
        // Given
        given(roomRepository.findById(anyString())).willReturn(Optional.empty());

        // When
        String result = underTest.execute(NAME, ROWS, COLUMNS);

        // Then
        assertThat(result, equalTo("Room successfully created"));
    }
}