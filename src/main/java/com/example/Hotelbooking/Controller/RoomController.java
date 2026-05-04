package com.example.Hotelbooking.Controller;

import com.example.Hotelbooking.DTO.RoomDTO;
import com.example.Hotelbooking.Entity.Room;
import com.example.Hotelbooking.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{hotelId}/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(@PathVariable int hotelId, @RequestParam String checkIn, @RequestParam String checkOut) {
        return new ResponseEntity<>(roomService.getAvailableRoomsByHotelId(hotelId, checkIn, checkOut), HttpStatus.OK);
    }


}
