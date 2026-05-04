package com.example.Hotelbooking.Service;

import com.example.Hotelbooking.DTO.HotelDTO;
import com.example.Hotelbooking.DTO.ModelMapper;
import com.example.Hotelbooking.Entity.Hotel;
import com.example.Hotelbooking.Entity.Room;
import com.example.Hotelbooking.Exception.HotelNotFoundException;
import com.example.Hotelbooking.Repository.HotelRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepo hotelRepo;
    private final BookingService bookingService;


    @Transactional
    public HotelDTO createHotel(Hotel hotel) {

        List<Room> rooms = new ArrayList<>();
        for(int i=1;i<=hotel.getTotalRooms();i++){
            Room room = new Room();
            room.setHotel(hotel);
            room.setRoomNumber(i);
            room.setPrice(1000);
            if(i%2==0){
                room.setRoomType(Room.RoomType.AC);
            }
            else{
                room.setRoomType(Room.RoomType.NON_AC);
            }
            rooms.add(room);
        }
        hotel.setRooms(rooms);
        hotelRepo.save(hotel);
        return ModelMapper.mapToHotelDTO(hotel);
    }
    @Transactional
    public HotelDTO updateHotel(Hotel hotel) {
        Hotel existingHotel = hotelRepo.findById(hotel.getId()).orElseThrow(()->new RuntimeException("Hotel not found"));
        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        existingHotel.setTotalRooms(hotel.getTotalRooms());
        if(existingHotel.getRooms()!=null) {
            existingHotel.getRooms().forEach(room -> room.setHotel(existingHotel));
        }
        hotelRepo.save(existingHotel);
        return ModelMapper.mapToHotelDTO(existingHotel);
    }
    public void deleteHotel(int id) {
        Hotel h =  hotelRepo.findById(id).orElseThrow(()->new RuntimeException("Hotel not found"));
        hotelRepo.deleteById(id);
//        return ModelMapper.mapToHotelDTO(h);
    }
    public List<HotelDTO> getAllHotels() {
        return hotelRepo.findAll()
                .stream()
                .map(ModelMapper::mapToHotelDTO)
                .toList();
    }

    public HotelDTO getHotelById(Integer id) {

        return ModelMapper.mapToHotelDTO(hotelRepo.findById(id).orElseThrow(()->new HotelNotFoundException("Hotel not found")));
    }


    @Transactional
    public List<HotelDTO> searchHotel(
            String location,
            Integer minPrice,
            Integer maxPrice,
            LocalDate checkIn,
            LocalDate checkOut
    ) {
        List<Hotel> hotels = hotelRepo.findByLocation(location);
        return hotels
                .stream()
                .filter(hotel -> hotel.getRooms().stream().anyMatch(
                        room -> room.getPrice()>=minPrice &&
                                room.getPrice()<=maxPrice &&
                                bookingService.isRoomAvailable(
                                        room.getId(),
                                        checkIn,
                                        checkOut
                                )
                        )
                )
                .map(ModelMapper::mapToHotelDTO)
                .toList();
    }

}
