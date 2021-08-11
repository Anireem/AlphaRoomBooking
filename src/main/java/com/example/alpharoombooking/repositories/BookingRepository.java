package com.example.alpharoombooking.repositories;

import com.example.alpharoombooking.models.Booking;
import com.example.alpharoombooking.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT booking FROM Booking booking " +
            "WHERE ((booking.start BETWEEN :start AND :finish) OR (booking.finish BETWEEN :start AND :finish)) " +
            "AND booking.room = :room " +
            "AND booking.id <> :id")
    List<Booking>getOverlappingBookings(@Param("start") LocalDateTime start,
                                       @Param("finish") LocalDateTime finish,
                                       @Param("room") Room room,
                                       @Param("id") Long id);

    default List<Booking> getOverlappingBookings(Booking booking) {
        return getOverlappingBookings(booking.getStart(), booking.getFinish(), booking.getRoom(), booking.getId());
    }
}
