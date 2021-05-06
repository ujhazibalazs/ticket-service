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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeleteRoomCommandTest {

    private DeleteRoomCommand underTest;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new DeleteRoomCommand(roomRepository);
    }

    @Test
    public void testExecuteShouldReturnRoomWithGivenNameDoesntExistWhenRoomNotFound() {
        // Given
        String name = "name";
        given(roomRepository.findById(name)).willReturn(Optional.empty());

        // When
        String result = underTest.execute(name);

        // Then
        assertThat(result, equalTo("Room with given name doesn't exist"));
    }

    @Test
    public void testExecuteShouldReturnRoomSuccessfullyDeletedWhenRoomFound() {
        // Given
        String name = "name";
        Room room = Mockito.mock(Room.class);
        given(roomRepository.findById(name)).willReturn(Optional.of(room));

        // When
        String result = underTest.execute(name);

        // Then
        assertThat(result, equalTo("Room successfully deleted"));
    }

}