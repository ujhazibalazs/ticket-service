package com.epam.training.ticketservice.domain;

public class User {

    private final String username;
    private final String password;
    private final Role role;
    private boolean signedIn;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.signedIn = false;
        this.role = role;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
