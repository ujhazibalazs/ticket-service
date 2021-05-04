package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.repository.MovieRepository;

import java.util.stream.Collectors;

public class ListMoviesCommand implements Command {

    private final MovieRepository movieRepository;

    public ListMoviesCommand(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public String execute() {
        String movies = movieRepository.getAllMovies()
                .stream()
                .map(movie -> movie.getName()
                            + " (" + movie.getGenre().toString().toLowerCase()
                            + ", " + movie.getLengthInMinutes()
                            + " minutes)")
                .collect(Collectors.joining(System.lineSeparator()));
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return movies;
    }
}
