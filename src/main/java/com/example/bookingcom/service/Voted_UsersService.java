package com.example.bookingcom.service;

import com.example.bookingcom.entities.Voted_Users;

import java.util.List;

public interface Voted_UsersService {

    List<Voted_Users> getAllVoted_Users();

    void saveVoted_User(Voted_Users voted_user);

}
