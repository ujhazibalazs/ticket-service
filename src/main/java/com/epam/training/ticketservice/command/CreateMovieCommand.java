package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
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
        Optional<Movie> foundMovie = movieRepository.findById(movieToAdd.getName());
        if (foundMovie.isPresent()) {
            return "Movie already exists";
        }
        movieRepository.save(movieToAdd);
        return "Movie created successfully";
    }
}
