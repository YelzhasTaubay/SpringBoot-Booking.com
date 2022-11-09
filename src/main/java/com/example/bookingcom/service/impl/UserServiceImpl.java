package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user=getUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User with name  not found");
        }
        return user;
    }
}
