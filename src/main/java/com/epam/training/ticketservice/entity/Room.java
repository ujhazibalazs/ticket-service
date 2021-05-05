package com.epam.training.ticketservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    private String name;
    private int rows;
    private int columns;

    public Room() {
    }

    public Room(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumberOfSeats() {
        return rows * columns;
    }
}
