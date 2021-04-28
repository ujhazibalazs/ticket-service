package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;

import java.util.Optional;

public class DeleteMovieCommand implements Command {

    private final MovieRepository movieRepository;
    private final String movieToDelete;

    public DeleteMovieCommand(MovieRepository movieRepository, String movieToDelete) {
        this.movieRepository = movieRepository;
        this.movieToDelete = movieToDelete;
    }

    @Override
    public String execute() {
        Optional<Movie> foundMovie = movieRepository.getAllMovies()
                .stream()
                .filter(movie -> movie.getName().equals(movieToDelete))
                .findFirst();
        if (foundMovie.isEmpty()) {
            return "Movie with given name doesn't exist";
        }
        movieRepository.deleteMovie(foundMovie.get());
        return "Movie deleted successfully";
    }
}
