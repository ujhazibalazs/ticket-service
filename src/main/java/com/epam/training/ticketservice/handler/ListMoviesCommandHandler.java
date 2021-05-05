package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.ListMoviesCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ListMoviesCommandHandler {

    private final ListMoviesCommand listMoviesCommand;

    public ListMoviesCommandHandler(ListMoviesCommand listMoviesCommand) {
        this.listMoviesCommand = listMoviesCommand;
    }

    @ShellMethod(value = "Lists all movies", key = "list movies")
    public String listMovies() {
        return listMoviesCommand.execute();
    }
}
