package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.SignInCommand;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class SignInCommandHandler {

    private final UserRepository userRepository;

    public SignInCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @ShellMethod(value = "Signs in as an administrator", key = "sign in privileged")
    public String signIn(String username, String password) {
        SignInCommand command = new SignInCommand(userRepository, username, password);
        return command.execute();
    }
}
