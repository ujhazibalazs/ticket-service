package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
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
class UpdateMovieCommandTest {

    private UpdateMovieCommand underTest;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new UpdateMovieCommand(movieRepository);
    }

    @Test
    public void testExecuteShouldReturnMovieWithGivenNameDoesntExistWhenMovieNotFound() {
        // Given
        String name = "name";
        Movie movie = Mockito.mock(Movie.class);
        given(movie.getName()).willReturn(name);
        given(movieRepository.findById(movie.getName())).willReturn(Optional.empty());

        // When
        String result = underTest.execute(movie);

        // Then
        assertThat(result, equalTo("Movie with given name doesn't exist"));
    }

    @Test
    public void testExecuteShouldReturnMovieUpdatedSuccessfullyWhenMovieFound() {
        // Given
        String name = "name";
        Movie movie = Mockito.mock(Movie.class);
        given(movie.getName()).willReturn(name);
        given(movieRepository.findById(movie.getName())).willReturn(Optional.of(movie));

        // When
        String result = underTest.execute(movie);

        // Then
        assertThat(result, equalTo("Movie updated successfully"));
    }
}