package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> getAll=userRepository.findAll();
        return getAll;
    }

    @Override
    public Users getUserByName(String name) {
        Users user=userRepository.findUsersByName(name);
        return user;
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        Optional<Users> user=userRepository.findById(id);
        return user;
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
