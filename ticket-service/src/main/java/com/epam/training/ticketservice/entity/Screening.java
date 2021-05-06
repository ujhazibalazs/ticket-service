package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.annotation.Generated;
import com.epam.training.ticketservice.id.ScreeningId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@IdClass(ScreeningId.class)
public class Screening implements Serializable {

    @Id
    private String movieName;
    @Id
    private String roomName;
    @Id
    private LocalDateTime startDate;

    private LocalDateTime createDate;

    public Screening(String movieName, String roomName, LocalDateTime startDate) {
        this.movieName = movieName;
        this.roomName = roomName;
        this.startDate = startDate;
        this.createDate = LocalDateTime.now();
    }

    public Screening() {
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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(movieName, screening.movieName) && Objects.equals(roomName, screening.roomName) && Objects.equals(startDate, screening.startDate) && Objects.equals(createDate, screening.createDate);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(movieName, roomName, startDate, createDate);
    }

    @Generated
    @Override
    public String toString() {
        return "Screening{" +
                "movieName='" + movieName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", startDate=" + startDate +
                ", createDate=" + createDate +
                '}';
    }
}