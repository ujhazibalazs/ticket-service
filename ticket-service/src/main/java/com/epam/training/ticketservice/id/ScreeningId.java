package com.epam.training.ticketservice.id;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ScreeningId implements Serializable {
    private String movieName;
    private String roomName;
    private LocalDateTime startDate;

    public ScreeningId(String movieName, String roomName, LocalDateTime startDate) {
        this.movieName = movieName;
        this.roomName = roomName;
        this.startDate = startDate;
    }

    public ScreeningId() {
    }
}
