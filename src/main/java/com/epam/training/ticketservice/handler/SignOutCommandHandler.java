package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.SignOutCommand;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class SignOutCommandHandler {

    private final UserRepository userRepository;

    public SignOutCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ShellMethod(value = "Signs out the currently logged in user", key = "sign out")
    public String signOut() {
        SignOutCommand command = new SignOutCommand(userRepository);
        return command.execute();
    }
}
