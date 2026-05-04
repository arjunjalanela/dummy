package com.example.Hotelbooking.Service;

import com.example.Hotelbooking.DTO.BookingDTO;
import com.example.Hotelbooking.DTO.BookingReqDTO;
import com.example.Hotelbooking.DTO.ModelMapper;
import com.example.Hotelbooking.Entity.Booking;
import com.example.Hotelbooking.Entity.Room;
import com.example.Hotelbooking.Entity.Users;
import com.example.Hotelbooking.Repository.BookingRepo;
import com.example.Hotelbooking.Repository.RoomRepo;
import com.example.Hotelbooking.Repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;
    private final UserRepo userRepo;

    @Transactional
    public boolean isRoomAvailable(int roomId, LocalDate checkInDate, LocalDate checkOutDate){
        return bookingRepo
                .findConflictingBookings(roomId,checkInDate,checkOutDate)
                .isEmpty();
    }

    @Transactional
    public BookingDTO book(BookingReqDTO req){
        Room room =roomRepo.findById(req.getRoomId()).orElseThrow(()->new RuntimeException("Room Not Found"));
        Users user = userRepo.findById(req.getUserId()).orElseThrow(()->new RuntimeException("User Not Found"));

        boolean isAvaliable=bookingRepo.findConflictingBookings(
                room.getId(),
                req.getCheckIn(),
                req.getCheckOut()
        ).isEmpty();

        if(!isAvaliable){
            throw new RuntimeException("Booking not avaliable");
        }


        Booking booking = new Booking();
        booking.setStatus(Booking.Status.BOOKED);
        booking.setBookingDate(LocalDateTime.now());
        booking.setRoom(room);
        booking.setUser(user);
        booking.setCheckInDate(req.getCheckIn());
        booking.setCheckOutDate(req.getCheckOut());

        bookingRepo.save(booking);
        return ModelMapper.mapToBookingDTO(booking);
    }

    @Transactional
    public BookingDTO  cancel(int bookingId){
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(()->new RuntimeException("Booking not found"));
        if(booking.getStatus().equals(Booking.Status.CANCELLED)){
            throw new RuntimeException("Booking already cancelled");
        }
        if(booking.getCheckInDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Cannot Cancel After Check In Date");
        }
        booking.setStatus(Booking.Status.CANCELLED);
        return ModelMapper.mapToBookingDTO(booking);
    }

    @Transactional
    public List<BookingDTO> getAllBookings(){
        List<Booking> bookings = bookingRepo.findAll();
        return ModelMapper.mapToBookingDTOs(bookings);
    }
    @Transactional
    public List<BookingDTO> getBookingsByUser(int userId){
        List<Booking> bookings= bookingRepo.findByUserId(userId);
        return ModelMapper.mapToBookingDTOs(bookings);
    }




}
