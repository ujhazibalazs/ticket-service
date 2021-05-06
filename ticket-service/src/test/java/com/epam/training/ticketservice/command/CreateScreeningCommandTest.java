package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
    public static final String GOOD_DATE_STRING = "2020-01-01 10:00";
    public static final String BAD_DATE_STRING = "badDate";
    public static final LocalDateTime GOOD_DATE = LocalDateTime.of(2020, 1, 1, 10, 0);
    public static final LocalDateTime BEFORE_DATE = LocalDateTime.of(2020, 1, 1, 9, 0);
    public static final LocalDateTime AFTER_DATE = LocalDateTime.of(2020, 1, 1, 11, 0);

    private CreateScreeningCommand underTest;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ScreeningRepository screeningRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CreateScreeningCommand(movieRepository, roomRepository, screeningRepository);
    }

    @Test
    public void testExecuteShouldReturnMovieDoesntExistWhenMovieNotFound() {
        // Given
        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.empty());

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

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
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Room doesn't exist"));
        verifyNoInteractions(screeningRepository);
    }

    @Test
    public void testExecuteShouldReturnBadDateFormatWhenBadDateGiven() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, BAD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Bad date format"));
        verifyNoInteractions(screeningRepository);
    }

    @Test
    public void testExecuteShouldReturnMovieInRepositoryDoesntExistWhenScreeningsMovieNotFound() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);
        Screening screening = Mockito.mock(Screening.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));
        given(screeningRepository.findAll()).willReturn(List.of(screening));

        given(screening.getMovieName()).willReturn("test");
        given(screening.getRoomName()).willReturn(ROOM_NAME);

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Movie in repository doesn't exist"));

    }

    @Test
    public void testExecuteShouldReturnThereIsAnOverlappingScreeningWhenAnotherScreeningStartsAtTheSameTime() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);
        Screening screening = Mockito.mock(Screening.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));
        given(screeningRepository.findAll()).willReturn(List.of(screening));

        given(screening.getMovieName()).willReturn(MOVIE_NAME);
        given(screening.getRoomName()).willReturn(ROOM_NAME);
        given(screening.getStartDate()).willReturn(GOOD_DATE);

        given(movie.getLengthInMinutes()).willReturn(10);

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("There is an overlapping screening"));
    }

    @Test
    public void testExecuteShouldReturnThisWouldStartInTheBreakPeriodWhenScreeningStartsAfterAnotherScreening() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);
        Screening screening = Mockito.mock(Screening.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));
        given(screeningRepository.findAll()).willReturn(List.of(screening));

        given(screening.getMovieName()).willReturn(MOVIE_NAME);
        given(screening.getRoomName()).willReturn(ROOM_NAME);
        given(screening.getStartDate()).willReturn(BEFORE_DATE);

        given(movie.getLengthInMinutes()).willReturn(60);

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("This would start in the break period after another screening in this room"));
    }

    @Test
    public void testExecuteShouldReturnThisWouldStartInTheBreakPeriodWhenScreeningStartsBeforeAnotherScreening() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);
        Screening screening = Mockito.mock(Screening.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));
        given(screeningRepository.findAll()).willReturn(List.of(screening));

        given(screening.getMovieName()).willReturn(MOVIE_NAME);
        given(screening.getRoomName()).willReturn(ROOM_NAME);
        given(screening.getStartDate()).willReturn(AFTER_DATE);

        given(movie.getLengthInMinutes()).willReturn(60);

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("This would start in the break period after another screening in this room"));
    }

    @Test
    public void testExecuteShouldReturnScreeningCreatedSuccessfullyWhenThereAreNoScreenings() {
        // Given
        Movie movie = Mockito.mock(Movie.class);
        Room room = Mockito.mock(Room.class);

        given(movieRepository.findById(MOVIE_NAME)).willReturn(Optional.of(movie));
        given(roomRepository.findById(ROOM_NAME)).willReturn(Optional.of(room));
        given(screeningRepository.findAll()).willReturn(Collections.emptyList());

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Screening created successfully"));
    }

}