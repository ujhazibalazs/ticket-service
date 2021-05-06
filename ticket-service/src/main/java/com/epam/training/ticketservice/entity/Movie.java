package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.annotation.Generated;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return lengthInMinutes == movie.lengthInMinutes && Objects.equals(name, movie.name) && Objects.equals(genre, movie.genre);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(name, genre, lengthInMinutes);
    }

    @Generated
    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                '}';
    }
}
