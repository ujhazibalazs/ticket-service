package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.DescribeAccountCommand;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DescribeAccountCommandHandler {

    private final UserRepository userRepository;

    public DescribeAccountCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ShellMethod(value = "Describes the account currently logged in", key = "describe account")
    public String describeAccount() {
        DescribeAccountCommand command = new DescribeAccountCommand(userRepository);
        return command.execute();
    }
}
