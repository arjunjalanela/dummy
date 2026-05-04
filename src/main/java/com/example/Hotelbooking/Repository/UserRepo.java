package com.example.Hotelbooking.Repository;

import com.example.Hotelbooking.DTO.UserDTO;
import com.example.Hotelbooking.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<UserDTO> findByEmail(String email);

    Optional<Users> findByName(String username);
}
