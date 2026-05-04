package com.example.Hotelbooking.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        BOOKED,
        CANCELLED
    }
    private LocalDateTime bookingDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
