package com.example.bookingcom.service;

import com.example.bookingcom.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Users getUserByEmail(String email);

    void addUser(Users user);

    List<Users> getAllUsers();

    Users getUserByName(String name);

    Optional<Users> getUserById(Long id);

}
