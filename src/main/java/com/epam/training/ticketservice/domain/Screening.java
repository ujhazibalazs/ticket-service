package com.epam.training.ticketservice.domain;

import java.time.LocalDateTime;

public class Screening {
    private final Movie movie;
    private final Room room;
    private final LocalDateTime startDate;

    public Screening(Movie movie, Room room, LocalDateTime startDate) {
        this.movie = movie;
        this.room = room;
        this.startDate = startDate;
    }
}
