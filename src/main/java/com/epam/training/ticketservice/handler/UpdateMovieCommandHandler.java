package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.UpdateMovieCommand;
import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class UpdateMovieCommandHandler {

    private final UpdateMovieCommand updateMovieCommand;
    private final Authenticator authenticator;

    public UpdateMovieCommandHandler(UpdateMovieCommand updateMovieCommand, Authenticator authenticator) {
        this.updateMovieCommand = updateMovieCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Updates a movie", key = "update movie")
    public String updateMovie(String name, String genre, int lengthInMinutes) {
        return updateMovieCommand.execute(new Movie(name, genre, lengthInMinutes));
    }

    @ShellMethodAvailability("update movie")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
