package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Movie;
import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.entity.Screening;
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
    private static final int BREAK_TIME = 10;

    public CreateScreeningCommand(MovieRepository movieRepository,
                                  RoomRepository roomRepository,
                                  ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.screeningRepository = screeningRepository;
    }

    public String execute(String movieName, String roomName, String startDateString) {
        Optional<Movie> foundMovie = movieRepository.findById(movieName);
        if (foundMovie.isEmpty()) {
            return "Movie doesn't exist";
        }
        Optional<Room> foundRoom = roomRepository.findById(roomName);
        if (foundRoom.isEmpty()) {
            return "Room doesn't exist";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateString, formatter);
        } catch (DateTimeParseException exception) {
            return "Bad date format";
        }
        LocalDateTime endDate = startDate.plusMinutes(foundMovie.get().getLengthInMinutes());
        List<Screening> screeningsInGivenRoom = screeningRepository.findAll()
                .stream()
                .filter(screening -> screening.getRoomName().equals(roomName))
                .collect(Collectors.toList());
        if (!screeningsInGivenRoom.isEmpty()) {
            for (Screening screening : screeningsInGivenRoom) {
                Optional<Movie> currentScreeningMovie = movieRepository.findById(screening.getMovieName());
                if (currentScreeningMovie.isEmpty()) {
                    return "Movie in repository doesn't exist";
                }
                LocalDateTime currentStartDate = screening.getStartDate();
                LocalDateTime currentEndDate = screening
                        .getStartDate()
                        .plusMinutes(currentScreeningMovie.get().getLengthInMinutes());
                if ((startDate.isAfter(currentStartDate) || startDate.isEqual(currentStartDate))
                        && startDate.isBefore(currentEndDate)) {
                    return "There is an overlapping screening";
                } else if (((startDate.isAfter(currentEndDate) || startDate.isEqual(currentEndDate))
                        && startDate.isBefore(currentEndDate.plusMinutes(BREAK_TIME)))
                        || (endDate.isAfter(currentStartDate.minusMinutes(BREAK_TIME))
                        && startDate.isBefore(currentStartDate))) {
                    return "This would start in the break period after another screening in this room";
                }
            }
        }
        screeningRepository.save(new Screening(movieName, roomName, startDate));
        return "Screening created successfully";
    }
}
