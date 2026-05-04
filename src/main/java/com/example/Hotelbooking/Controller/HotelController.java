package com.example.Hotelbooking.Controller;

import com.example.Hotelbooking.DTO.HotelDTO;
import com.example.Hotelbooking.Entity.Hotel;
import com.example.Hotelbooking.Service.HotelService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<List<HotelDTO>> searchHotels(@RequestParam String location,
                                                      @RequestParam int minPrice,
                                                      @RequestParam int maxPrice,
                                                      @RequestParam String checkIn,
                                                      @RequestParam String checkOut
                                       ){
        return new ResponseEntity<>(hotelService.searchHotel(location,minPrice,maxPrice,LocalDate.parse(checkIn),LocalDate.parse(checkOut)), HttpStatus.OK);
    }

}
