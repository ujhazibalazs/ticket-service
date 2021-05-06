package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.id.ScreeningId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
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
}