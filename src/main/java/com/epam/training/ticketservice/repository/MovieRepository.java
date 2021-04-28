package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.domain.Movie;

import java.util.List;

public interface MovieRepository {
    void addMovie(Movie movieToAdd);
    void updateMovie(Movie movieToUpdate);
    void deleteMovie(Movie movieToDelete);
    List<Movie> getAllMovies();
}
