package com.example.Hotelbooking.Repository;

import com.example.Hotelbooking.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    @Query("""
            SELECT b FROM Booking b
            WHERE b.room.id = :roomId
            AND b.status = 'BOOKED'
            AND b.checkInDate < :checkOut
            AND b.checkOutDate > :checkIn
            """)
    List<Booking> findConflictingBookings(int roomId, LocalDate checkIn, LocalDate checkOut);

    List<Booking> findByUserId(int userId);
}
