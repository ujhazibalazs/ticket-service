package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;

import java.util.Optional;

public class CreateMovieCommand implements Command {

    private final MovieRepository movieRepository;
    private final Movie movieToAdd;

    public CreateMovieCommand(MovieRepository movieRepository, Movie movieToAdd) {
        this.movieRepository = movieRepository;
        this.movieToAdd = movieToAdd;
    }

    @Override
    public String execute() {
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
