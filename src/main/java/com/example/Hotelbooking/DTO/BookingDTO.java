package com.example.Hotelbooking.DTO;


import com.example.Hotelbooking.Entity.Booking;
import com.example.Hotelbooking.Entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private int id;
    private Booking.Status status;
    private LocalDateTime date;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer roomNumber;
    private String hotelName;
    private Room.RoomType roomType;

}
