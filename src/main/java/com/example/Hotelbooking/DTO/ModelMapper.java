package com.example.Hotelbooking.DTO;

import com.example.Hotelbooking.Entity.Booking;
import com.example.Hotelbooking.Entity.Hotel;
import com.example.Hotelbooking.Entity.Room;
import com.example.Hotelbooking.Entity.Users;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {
    public static UserDTO mapToUserDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    public static HotelDTO mapToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setLocation(hotel.getLocation());
        return hotelDTO;
    }
    public static BookingDTO mapToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setDate(booking.getBookingDate());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setRoomNumber(booking.getRoom().getRoomNumber());
        bookingDTO.setHotelName(booking.getRoom().getHotel().getName());
        bookingDTO.setRoomType(booking.getRoom().getRoomType());
        return bookingDTO;
    }
    public static List<BookingDTO> mapToBookingDTOs(List<Booking> bookings) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        bookingDTOs = bookings.stream().map(ModelMapper::mapToBookingDTO).toList();
        return bookingDTOs;
    }
    public static RoomDTO mapToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomType(String.valueOf(room.getRoomType()));
        roomDTO.setPrice(room.getPrice());
        roomDTO.setHotelid(room.getHotel().getId());
        roomDTO.setRoomnumber(room.getRoomNumber());
        return roomDTO;
    }
    public static List<RoomDTO> mapToRoomDTOs(List<Room> rooms) {
        List<RoomDTO> roomDTOs = new ArrayList<>();
        return rooms.stream().map(ModelMapper::mapToRoomDTO).toList();
    }
}

