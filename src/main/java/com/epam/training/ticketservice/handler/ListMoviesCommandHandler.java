package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.ListMoviesCommand;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ListMoviesCommandHandler {

    private final MovieRepository movieRepository;

    public ListMoviesCommandHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ShellMethod(value = "Lists all the movies", key = "list movies")
    public String listMovies() {
        ListMoviesCommand command = new ListMoviesCommand(movieRepository);
        return command.execute();
    }
}
