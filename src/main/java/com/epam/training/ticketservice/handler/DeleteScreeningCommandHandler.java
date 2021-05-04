package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DeleteScreeningCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DeleteScreeningCommandHandler {

    private final DeleteScreeningCommand command;

    public DeleteScreeningCommandHandler(DeleteScreeningCommand command) {
        this.command = command;
    }

    @ShellMethod(value = "Deletes an existing screening", key = "delete screening")
    public String deleteScreening(String movieName, String roomName, String startDate) {
        return command.execute(movieName, roomName, startDate);
    }
}
