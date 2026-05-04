package com.example.Hotelbooking.Controller;

import com.example.Hotelbooking.DTO.HotelDTO;
import com.example.Hotelbooking.Entity.Hotel;
import com.example.Hotelbooking.Service.HotelService;
import com.example.Hotelbooking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final HotelService hotelService;
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<HotelDTO> updateHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.updateHotel(hotel), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int id){
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/all")
    public ResponseEntity<List<HotelDTO>> findAllHotel(){

        return new ResponseEntity<>(hotelService.getAllHotels(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HotelDTO> findHotelById(@PathVariable int id){

        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK)   ;
    }
}
