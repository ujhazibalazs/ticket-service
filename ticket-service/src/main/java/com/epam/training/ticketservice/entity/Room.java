package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.annotation.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return rows == room.rows && columns == room.columns && Objects.equals(name, room.name);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(name, rows, columns);
    }

    @Generated
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
