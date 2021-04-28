package com.epam.training.ticketservice.domain;

public class Movie {
    private final String name;
    private final Genre genre;
    private final int lengthInMinutes;

    public Movie(String name, Genre genre, int lengthInMinutes) {
        this.name = name;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }
}
