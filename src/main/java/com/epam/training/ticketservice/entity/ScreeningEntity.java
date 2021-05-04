package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.id.ScreeningId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(ScreeningId.class)
public class ScreeningEntity implements Serializable {

    @Id
    private String movieName;
    @Id
    private String roomName;
    @Id
    private LocalDateTime startDate;

    public ScreeningEntity(String movieName, String roomName, LocalDateTime startDate) {
        this.movieName = movieName;
        this.roomName = roomName;
        this.startDate = startDate;
    }

    public ScreeningEntity() {
    }

    public String getMovieName() {
        return movieName;
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}