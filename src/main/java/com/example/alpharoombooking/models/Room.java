package com.example.alpharoombooking.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="rooms")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    public Booking getNextBooking() {
        List<Booking> bookings = this.bookings.stream().filter(x -> x.getStart().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        bookings.sort((o1, o2) -> o1.getStart().compareTo(o2.getStart()));
        if (bookings.size() > 0)
            return bookings.get(0);
        else
            return null;
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
