package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    private String name;
    private int rows;
    private int columns;

    public int getNumberOfSeats() {
        return rows * columns;
    }

}
