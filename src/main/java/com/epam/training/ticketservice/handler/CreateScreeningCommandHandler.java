package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.CreateScreeningCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class CreateScreeningCommandHandler {

    private final CreateScreeningCommand createScreeningCommand;

    public CreateScreeningCommandHandler(CreateScreeningCommand createScreeningCommand) {
        this.createScreeningCommand = createScreeningCommand;
    }

    @ShellMethod(value = "Creates a new screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String startDate) {
        return createScreeningCommand.execute(movieName, roomName, startDate);
    }
}
