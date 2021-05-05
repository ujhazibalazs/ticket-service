package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.SignOutCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class SignOutCommandHandler {

    private final SignOutCommand signOutCommand;

    public SignOutCommandHandler(SignOutCommand signOutCommand) {
        this.signOutCommand = signOutCommand;
    }

    @ShellMethod(value = "Signs out the currently logged in user", key = "sign out")
    public String signOut() {
        return signOutCommand.execute();
    }
}
