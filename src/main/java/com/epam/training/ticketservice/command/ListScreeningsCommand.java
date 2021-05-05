package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListScreeningsCommand implements Command {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    public ListScreeningsCommand(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public String execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Screening> screenings = screeningRepository.findAll();
        List<String> lines = new ArrayList<>();
        for (Screening screening : screenings) {
            Optional<Movie> foundMovie = movieRepository.findById(screening.getMovieName());
            if (foundMovie.isEmpty()) {
                return "movie doesn't exist in repository";
            }
            lines.add(foundMovie.get().getName()
                    + " ("
                    + foundMovie.get().getGenre()
                    + ", "
                    + foundMovie.get().getLengthInMinutes()
                    + " minutes), screened in room "
                    + screening.getRoomName()
                    + ", at "
                    + screening.getStartDate().format(formatter));
        }
        String screeningsString = lines.stream().collect(Collectors.joining(System.lineSeparator()));
        if (screeningsString.isEmpty()) {
            return "There are no screenings";
        }
        return screeningsString;
    }
}
