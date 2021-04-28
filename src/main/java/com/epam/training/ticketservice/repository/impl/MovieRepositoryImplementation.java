package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImplementation implements MovieRepository {

    private List<Movie> movies = new ArrayList<>();

    @Override
    public void addMovie(Movie movieToAdd) {
        movies.add(movieToAdd);
    }

    @Override
    public void updateMovie(Movie movieToUpdate) {
        Optional<Movie> foundMovie = movies.stream()
                .filter(movie -> movie.getName().equals(movieToUpdate.getName()))
                .findFirst();
        foundMovie.ifPresent(movie -> movies.set(movies.indexOf(movie), movieToUpdate));
    }

    @Override
    public void deleteMovie(Movie movieToDelete) {
        Optional<Movie> foundMovie = movies.stream()
                .filter(movie -> movie.getName().equals(movieToDelete.getName()))
                .findFirst();
        foundMovie.ifPresent(movie -> movies.remove(movie));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }
}
