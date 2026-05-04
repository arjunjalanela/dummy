package com.example.Hotelbooking.Security;

import com.example.Hotelbooking.Entity.Users;
import com.example.Hotelbooking.Repository.UserRepo;
import com.example.Hotelbooking.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(users);
    }
}
