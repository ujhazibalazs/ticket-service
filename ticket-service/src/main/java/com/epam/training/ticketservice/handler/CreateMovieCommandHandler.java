package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateMovieCommand;
import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class CreateMovieCommandHandler {

    private final CreateMovieCommand createMovieCommand;
    private final Authenticator authenticator;

    public CreateMovieCommandHandler(CreateMovieCommand createMovieCommand, Authenticator authenticator) {
        this.createMovieCommand = createMovieCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Creates a new movie", key = "create movie")
    public String createMovie(String name, String genre, int lengthInMinutes) {
        return createMovieCommand.execute(new Movie(name, genre, lengthInMinutes));
    }

    @ShellMethodAvailability("create movie")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
