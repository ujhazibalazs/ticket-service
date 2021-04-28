package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;

import java.util.Optional;

public class UpdateMovieCommand implements Command {

    private final MovieRepository movieRepository;
    private final Movie movieToUpdate;

    public UpdateMovieCommand(MovieRepository movieRepository, Movie movieToUpdate) {
        this.movieRepository = movieRepository;
        this.movieToUpdate = movieToUpdate;
    }

    @Override
    public String execute() {
        Optional<Movie> foundMovie = movieRepository.getAllMovies()
                .stream()
                .filter(movie -> movie.getName().equals(movieToUpdate.getName()))
                .findFirst();
        if (foundMovie.isEmpty()) {
            return "Movie with given name doesn't exist";
        }
        movieRepository.updateMovie(movieToUpdate);
        return "Movie updated successfully";
    }
}
