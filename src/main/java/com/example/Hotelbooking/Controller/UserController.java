package com.example.Hotelbooking.Controller;


import com.example.Hotelbooking.DTO.AuthResponse;
import com.example.Hotelbooking.DTO.LoginReq;
import com.example.Hotelbooking.DTO.UserDTO;
import com.example.Hotelbooking.Entity.Users;
import com.example.Hotelbooking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody Users user){

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginReq req){
        return new ResponseEntity<>(userService.login(req.getUsername(), req.getPassword()), HttpStatus.OK);
    }
}
