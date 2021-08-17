package com.example.alpharoombooking.restcontrollers;

import com.example.alpharoombooking.exceptions.ResourceNotFoundException;
import com.example.alpharoombooking.models.Booking;
import com.example.alpharoombooking.models.Room;
import com.example.alpharoombooking.repositories.BookingRepository;
import com.example.alpharoombooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookingRestController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Get all
    @GetMapping("bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get by id
    @GetMapping("bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found, id: " + id));
        return ResponseEntity.ok().body(booking);
    }

    // Save
    @PostMapping("bookings")
    public Booking saveBooking(@RequestBody Booking booking) {
        Long roomId = booking.getRoom().getId();
        Room room  = roomRepository.findById(roomId).get();
        booking.setRoom(room);
        return bookingRepository.save(booking);
    }

    // Update
    @PutMapping("bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @RequestBody Booking booking) throws ResourceNotFoundException {
        Long roomId = booking.getRoom().getId();
        Room room  = roomRepository.findById(roomId).get();
        booking.setRoom(room);
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    // Delete
    @DeleteMapping("bookings/{id}")
    public Map<String, Boolean> deleteBooking(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found, id: " + id));

        bookingRepository.delete(booking);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
