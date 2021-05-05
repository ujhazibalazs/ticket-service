package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.ListScreeningsCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ListScreeningsCommandHandler {

    private final ListScreeningsCommand listScreeningsCommand;

    public ListScreeningsCommandHandler(ListScreeningsCommand listScreeningsCommand) {
        this.listScreeningsCommand = listScreeningsCommand;
    }

    @ShellMethod(value = "Lists all screenings", key = "list screenings")
    public String listScreenings() {
        return listScreeningsCommand.execute();
    }
}
