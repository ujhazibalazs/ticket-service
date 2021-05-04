package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.domain.Movie;
import com.epam.training.ticketservice.entity.RoomEntity;
import com.epam.training.ticketservice.entity.ScreeningEntity;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreateScreeningCommand {

    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ScreeningRepository screeningRepository;

    public CreateScreeningCommand(MovieRepository movieRepository, RoomRepository roomRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.screeningRepository = screeningRepository;
    }

    public String execute(String movieName, String roomName, String startDateString) {
        Optional<Movie> foundMovie = movieRepository.getAllMovies()
                .stream()
                .filter(movie -> movie.getName().equals(movieName))
                .findFirst();
        if (foundMovie.isEmpty()) {
            return "Movie doesn't exist";
        }
        Optional<RoomEntity> foundRoom = roomRepository.findById(roomName);
        if (foundRoom.isEmpty()) {
            return "Room doesn't exist";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateString, formatter);
        }
        catch (DateTimeParseException exception) {
            return "Bad date format";
        }
        LocalDateTime endDate = startDate.plusMinutes(foundMovie.get().getLengthInMinutes());
        List<ScreeningEntity> screeningsInGivenRoom = screeningRepository.findAll()
                .stream()
                .filter(screeningEntity -> screeningEntity.getRoomName().equals(roomName))
                .collect(Collectors.toList());
        if (!screeningsInGivenRoom.isEmpty()) {
            for (ScreeningEntity screeningEntity : screeningsInGivenRoom) {
                Optional<Movie> currentScreeningMovie = movieRepository.getAllMovies()
                        .stream()
                        .filter(movie -> movie.getName().equals(screeningEntity.getMovieName()))
                        .findFirst();
                if (currentScreeningMovie.isEmpty()) {
                    return "Movie in repository doesn't exist";
                }
                LocalDateTime currentStartDate = screeningEntity.getStartDate();
                LocalDateTime currentEndDate = screeningEntity.getStartDate().plusMinutes(currentScreeningMovie.get().getLengthInMinutes());
                int BREAK_TIME = 10;
                if ((startDate.isAfter(currentStartDate) || startDate.isEqual(currentStartDate)) && startDate.isBefore(currentEndDate)) {
                    return "There is an overlapping screening";
                }
                else if (((startDate.isAfter(currentEndDate) || startDate.isEqual(currentEndDate)) && startDate.isBefore(currentEndDate.plusMinutes(BREAK_TIME)))
                        || (endDate.isAfter(currentStartDate.minusMinutes(BREAK_TIME)) && startDate.isBefore(currentStartDate))) {
                    return "This would start in the break period after another screening in this room";
                }
            }
        }
        screeningRepository.save(new ScreeningEntity(movieName, roomName, startDate));
        return "Screening created successfully";
    }
}
