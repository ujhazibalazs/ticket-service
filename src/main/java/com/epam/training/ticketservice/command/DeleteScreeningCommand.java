package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.id.ScreeningId;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class DeleteScreeningCommand {

    private final ScreeningRepository screeningRepository;

    public DeleteScreeningCommand(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public String execute(String movieName, String roomName, String startDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateString, formatter);
        } catch (DateTimeParseException exception) {
            return "Bad date format";
        }
        Optional<Screening> foundScreening = screeningRepository
                .findById(new ScreeningId(movieName, roomName, startDate));
        if (foundScreening.isEmpty()) {
            return "Screening doesn't exist";
        }
        screeningRepository.delete(foundScreening.get());
        return "Screening deleted successfully";
    }
}
