package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateMovieCommand {

    private final MovieRepository movieRepository;

    public UpdateMovieCommand(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String execute(Movie movieToUpdate) {
        Optional<Movie> foundMovie = movieRepository.findById(movieToUpdate.getName());
        if (foundMovie.isEmpty()) {
            return "Movie with given name doesn't exist";
        }
        movieRepository.save(movieToUpdate);
        return "Movie updated successfully";
    }
}
