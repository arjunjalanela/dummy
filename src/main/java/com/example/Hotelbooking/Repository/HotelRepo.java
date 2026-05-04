package com.example.Hotelbooking.Repository;

import com.example.Hotelbooking.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {


    List<Hotel> findByLocation(String location);
}
