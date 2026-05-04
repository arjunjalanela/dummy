package com.example.Hotelbooking.Service;

import com.example.Hotelbooking.DTO.ModelMapper;
import com.example.Hotelbooking.DTO.RoomDTO;
import com.example.Hotelbooking.Entity.Hotel;
import com.example.Hotelbooking.Entity.Room;
import com.example.Hotelbooking.Repository.BookingRepo;
import com.example.Hotelbooking.Repository.HotelRepo;
import com.example.Hotelbooking.Repository.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final BookingRepo bookingRepo;


    public List<RoomDTO> getAllRoomsByHotelId(int id) {
        Hotel hotel = hotelRepo.findById(id).orElseThrow(()->new RuntimeException("Hotel not found"));
        List<Room> rooms= hotel.getRooms();
        return rooms.stream().map(ModelMapper::mapToRoomDTO).toList();
    }
    public List<RoomDTO> getAvailableRoomsByHotelId(int id, String checkIn, String checkOut) {
        List<Room> rooms= roomRepo.findByHotelId(id);
        List<Room> availableRooms = rooms.stream()
                .filter(room -> bookingRepo.findConflictingBookings(
                        room.getId(),
                        LocalDate.parse(checkIn),
                        LocalDate.parse(checkOut)
                ).isEmpty()
                ).toList();
        return availableRooms.stream().map(ModelMapper::mapToRoomDTO).toList();
    }

}
