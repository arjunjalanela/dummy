package com.example.Hotelbooking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    // 👇 Enum lives inside the entity
    public enum RoomType {
        AC,
        NON_AC
    }
    private Integer roomNumber;

    private Integer price;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Booking> booking;


}
