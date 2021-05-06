package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CreateScreeningCommandTest {

    public static final String MOVIE_NAME = "movieName";
    public static final String ROOM_NAME = "roomName";
    public static final String START_DATE = "startDateString";

    @Mock
    MovieRepository movieRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    ScreeningRepository screeningRepository;

    CreateScreeningCommand underTest = new CreateScreeningCommand(movieRepository, roomRepository, screeningRepository);

    @Test
    public void testExecuteShouldReturnMovieDoesntExistWhenMovieNotFound() {
        // Given
        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.empty());

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, START_DATE);

        // Then
        assertThat(result, equalTo("Movie doesn't exist"));
        verifyNoInteractions(roomRepository, screeningRepository);
        verify(movieRepository).findById(MOVIE_NAME);
    }

    @Test
    public void testExecuteShouldReturnRoomDoesntExistWhenRoomNotFound() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.empty());

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, START_DATE);

        // Then
        assertThat(result, equalTo("Room doesn't exist"));
        verifyNoInteractions(screeningRepository);
    }

}