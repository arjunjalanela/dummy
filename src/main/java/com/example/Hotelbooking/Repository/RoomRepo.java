package com.example.Hotelbooking.Repository;

import com.example.Hotelbooking.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

    List<Room> findByHotelId(int id);
}
