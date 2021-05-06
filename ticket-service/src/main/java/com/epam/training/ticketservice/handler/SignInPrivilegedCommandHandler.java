package com.epam.training.ticketservice.handler;

import com.epam.training.ticketservice.command.SignInPrivilegedCommand;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class SignInPrivilegedCommandHandler {

    private final SignInPrivilegedCommand signInPrivilegedCommand;

    public SignInPrivilegedCommandHandler(SignInPrivilegedCommand signInPrivilegedCommand) {
        this.signInPrivilegedCommand = signInPrivilegedCommand;
    }

    @ShellMethod(value = "Signs in as administrator", key = "sign in privileged")
    public String signInPrivileged(String username, String password) {
        return signInPrivilegedCommand.execute(username, password);
    }
}
