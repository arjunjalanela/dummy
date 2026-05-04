package com.example.Hotelbooking.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private Integer id;
    private Integer roomnumber;
    private String roomType;
    private Integer price;
    private Integer hotelid;

}
