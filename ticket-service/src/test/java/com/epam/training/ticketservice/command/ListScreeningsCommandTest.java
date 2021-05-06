package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ListScreeningsCommandTest {

    private ListScreeningsCommand underTest;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ListScreeningsCommand(screeningRepository, movieRepository);
    }

    @Test
    public void testExecuteShouldReturnThereAreNoScreeningsWhenNoScreeningsFound() {
        // Given
        given(screeningRepository.findAllByOrderByCreateDate()).willReturn(Collections.emptyList());

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("There are no screenings"));
        verifyNoInteractions(movieRepository);
    }

    @Test
    public void testExecuteShouldReturnMovieDoesntExistInRepositoryWhenScreeningMovieNotFound() {
        // Given
        Screening screening = Mockito.mock(Screening.class);
        given(screeningRepository.findAllByOrderByCreateDate()).willReturn(List.of(screening));
        given(movieRepository.findById(any())).willReturn(Optional.empty());

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("Movie doesn't exist in repository"));
    }

    @Test
    public void testExecuteShouldReturnStringOfScreeningsWhenOneOrMoreScreeningsAreFound() {
        // Given
        String movieName = "movieName";
        String movieGenre = "movieGenre";
        int movieLength = 60;
        String roomName = "roomName";
        LocalDateTime startDate = LocalDateTime.of(2020, 1, 1, 10, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Movie movie = Mockito.mock(Movie.class);
        Screening screening = Mockito.mock(Screening.class);
        given(screeningRepository.findAllByOrderByCreateDate()).willReturn(List.of(screening));
        given(movieRepository.findById(any())).willReturn(Optional.of(movie));

        given(movie.getName()).willReturn(movieName);
        given(movie.getGenre()).willReturn(movieGenre);
        given(movie.getLengthInMinutes()).willReturn(movieLength);
        given(screening.getRoomName()).willReturn(roomName);
        given(screening.getStartDate()).willReturn(startDate);

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo(movieName
                + " ("
                + movieGenre
                + ", "
                + movieLength
                + " minutes), screened in room "
                + roomName
                + ", at "
                + startDate.format(formatter)));
    }
}