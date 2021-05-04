package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateMovieCommand {

    private final MovieRepository movieRepository;

    public CreateMovieCommand(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String execute(Movie movieToAdd) {
        Optional<Movie> foundMovie = movieRepository.getAllMovies()
                .stream()
                .filter(movie -> movie.getName().equals(movieToAdd.getName()))
                .findFirst();
        if (foundMovie.isPresent()) {
            return "Movie already exists.";
        }
        movieRepository.addMovie(movieToAdd);
        return "Movie created successfully.";
    }
}
