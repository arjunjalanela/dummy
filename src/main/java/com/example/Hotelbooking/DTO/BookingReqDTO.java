package com.example.Hotelbooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingReqDTO {
    private Integer roomId;
    private Integer userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
