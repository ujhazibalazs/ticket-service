package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DescribeAccountCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DescribeAccountCommandHandler {

    private final DescribeAccountCommand describeAccountCommand;

    public DescribeAccountCommandHandler(DescribeAccountCommand describeAccountCommand) {
        this.describeAccountCommand = describeAccountCommand;
    }

    @ShellMethod(value = "Describes the account currently logged in", key = "describe account")
    public String describeAccount() {
        return describeAccountCommand.execute();
    }
}
