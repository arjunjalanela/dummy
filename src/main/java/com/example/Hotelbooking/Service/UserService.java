package com.example.Hotelbooking.Service;


import com.example.Hotelbooking.DTO.AuthResponse;
import com.example.Hotelbooking.DTO.ModelMapper;
import com.example.Hotelbooking.DTO.UserDTO;
import com.example.Hotelbooking.Entity.Users;
import com.example.Hotelbooking.Exception.UserNotFoundException;
import com.example.Hotelbooking.Repository.UserRepo;
import com.example.Hotelbooking.Security.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtility jwtUtility;

    public UserDTO createUser(Users user) {
        Users newUser = new Users();
        newUser.setRole(Users.Role.USER);
        newUser.setName(user.getName());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        return ModelMapper.mapToUserDTO(userRepo.save(newUser));
    }
    public AuthResponse login(String username, String password) {
        Users user= userRepo.findByName(username).orElseThrow(()->new UserNotFoundException(username+"User not found"));
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🎟️ generate token
        String token = jwtUtility.generateToken(user.getName());

        return new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getRole().name()
        );

    }


}
