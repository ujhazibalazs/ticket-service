package com.epam.training.ticketservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    private String name;
    private String genre;
    private int lengthInMinutes;

    public Movie(String name, String genre, int lengthInMinutes) {
        this.name = name;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }
}
