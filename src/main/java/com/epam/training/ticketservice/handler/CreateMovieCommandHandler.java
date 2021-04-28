package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateMovieCommand;
import com.epam.training.ticketservice.domain.Genre;
import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CreateMovieCommandHandler {

    private final MovieRepository movieRepository;

    public CreateMovieCommandHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ShellMethod(value = "Create a new movie", key = "create movie")
    public String createMovie(String name, String genre, int lengthInMinutes) {
        CreateMovieCommand command = new CreateMovieCommand(movieRepository, new Movie(name, Genre.valueOf(genre.toUpperCase()), lengthInMinutes));
        return command.execute();
    }
}
