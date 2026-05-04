package com.example.Hotelbooking.Controller;


import com.example.Hotelbooking.DTO.BookingDTO;
import com.example.Hotelbooking.DTO.BookingReqDTO;
import com.example.Hotelbooking.Entity.Booking;
import com.example.Hotelbooking.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


    @PostMapping("/book")
    public BookingDTO bookHotel(@RequestBody BookingReqDTO booking){
        return bookingService.book(booking);
    }
    @DeleteMapping("/delete/{id}")
    public BookingDTO deleteBook(@PathVariable int id){
        return bookingService.cancel(id);
    }
    @GetMapping("/all/{id}")
    public List<BookingDTO> findAllBookingsByUser(@PathVariable int id){
        return bookingService.getBookingsByUser(id);
    }


}
