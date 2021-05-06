package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ListMoviesCommand implements Command {

    private final MovieRepository movieRepository;

    public ListMoviesCommand(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String execute() {
        String movies = movieRepository.findAll()
                .stream()
                .map(movie -> movie.getName()
                            + " (" + movie.getGenre()
                            + ", " + movie.getLengthInMinutes()
                            + " minutes)")
                .collect(Collectors.joining(System.lineSeparator()));
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return movies;
    }
}
