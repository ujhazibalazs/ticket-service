package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteMovieCommand;
import com.epam.training.ticketservice.security.Authenticator;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class DeleteMovieCommandHandler {

    private final DeleteMovieCommand deleteMovieCommand;
    private final Authenticator authenticator;

    public DeleteMovieCommandHandler(DeleteMovieCommand deleteMovieCommand, Authenticator authenticator) {
        this.deleteMovieCommand = deleteMovieCommand;
        this.authenticator = authenticator;
    }

    @ShellMethod(value = "Deletes a movie", key = "delete movie")
    public String deleteMovie(String name) {
        return deleteMovieCommand.execute(name);
    }

    @ShellMethodAvailability("delete movie")
    public Availability isAdminSignedIn() {
        return authenticator.isAdmin() ? Availability.available() : Availability.unavailable("Permission denied");
    }
}
