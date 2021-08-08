package com.example.alpharoombooking.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int numberOfSeats;
    private boolean projector;
    private boolean board;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
    private List<Booking> bookings;

    public Room() {
    }

    public Room(Long id, String name, int numberOfSeats, boolean projector, boolean board, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.projector = projector;
        this.board = board;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isBoard() {
        return board;
    }

    public void setBoard(boolean board) {
        this.board = board;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
