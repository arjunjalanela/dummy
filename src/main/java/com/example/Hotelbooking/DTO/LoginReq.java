package com.example.Hotelbooking.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LoginReq {
    private String username;
    private String password;
}
