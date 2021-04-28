package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.UpdateMovieCommand;
import com.epam.training.ticketservice.domain.Genre;
import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UpdateMovieCommandHandler {

    private final MovieRepository movieRepository;

    public UpdateMovieCommandHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ShellMethod(value = "Update an existing movie", key = "update movie")
    public String updateMovie(String name, String genre, int lengthInMinutes) {
        UpdateMovieCommand command = new UpdateMovieCommand(movieRepository, new Movie(name, Genre.valueOf(genre.toUpperCase()), lengthInMinutes));
        return command.execute();
    }
}
