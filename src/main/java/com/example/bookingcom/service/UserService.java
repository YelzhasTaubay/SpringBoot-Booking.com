package com.example.bookingcom.service;

import com.example.bookingcom.entities.Users;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users getUserByEmail(String email);

    void addUser(Users user);



}
