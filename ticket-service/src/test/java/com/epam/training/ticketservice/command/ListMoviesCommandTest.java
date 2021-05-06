package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ListMoviesCommandTest {

    private ListMoviesCommand underTest;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new ListMoviesCommand(movieRepository);
    }

    @Test
    public void testExecuteShouldReturnThereAreNoMoviesAtTheMomentWhenMovieNotFound() {
        // Given
        given(movieRepository.findAll()).willReturn(Collections.emptyList());

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("There are no movies at the moment"));
    }

    @Test
    public void testExecuteShouldReturnMoviesWhenMoviesFound() {
        // Given
        String name = "name";
        String genre = "genre";
        int length = 60;

        Movie movie = Mockito.mock(Movie.class);

        given(movieRepository.findAll()).willReturn(List.of(movie));
        given(movie.getName()).willReturn(name);
        given(movie.getGenre()).willReturn(genre);
        given(movie.getLengthInMinutes()).willReturn(length);

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo(name
                + " ("
                + genre
                + ", "
                + length
                + " minutes)"));
    }
}