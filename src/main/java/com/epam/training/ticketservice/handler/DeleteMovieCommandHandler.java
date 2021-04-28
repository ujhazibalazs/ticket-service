package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteMovieCommand;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DeleteMovieCommandHandler {

    private final MovieRepository movieRepository;

    public DeleteMovieCommandHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ShellMethod(value = "Delete a movie", key = "delete movie")
    public String deleteMovie(String name) {
        DeleteMovieCommand command = new DeleteMovieCommand(movieRepository, name);
        return command.execute();
    }
}
