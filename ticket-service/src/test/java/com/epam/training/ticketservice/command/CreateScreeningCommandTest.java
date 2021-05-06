package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class CreateScreeningCommandTest {

    public static final String MOVIE_NAME = "movieName";
    public static final String ROOM_NAME = "roomName";
    public static final String START_DATE = "startDateString";

    @Test
    public void testExecuteShouldReturnMovieDoesntExistWhenMovieNotFound() {
        // Given
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ScreeningRepository screeningRepository = Mockito.mock(ScreeningRepository.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.empty());

        CreateScreeningCommand underTest = new CreateScreeningCommand(movieRepository, roomRepository, screeningRepository);

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, START_DATE);

        // Then
        assertThat(result, equalTo("Movie doesn't exist"));
        verifyNoInteractions(roomRepository, screeningRepository);
        verify(movieRepository).findById(MOVIE_NAME);
    }

}