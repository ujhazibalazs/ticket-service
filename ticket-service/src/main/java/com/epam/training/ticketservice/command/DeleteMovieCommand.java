package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteMovieCommand {

    private final MovieRepository movieRepository;

    public DeleteMovieCommand(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String execute(String movieToDelete) {
        Optional<Movie> foundMovie = movieRepository.findById(movieToDelete);
        if (foundMovie.isEmpty()) {
            return "Movie with given name doesn't exist";
        }
        movieRepository.delete(foundMovie.get());
        return "Movie deleted successfully";
    }
}
