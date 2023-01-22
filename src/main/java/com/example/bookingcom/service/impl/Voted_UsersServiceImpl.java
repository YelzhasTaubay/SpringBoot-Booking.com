package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.Voted_UsersRepository;
import com.example.bookingcom.entities.Voted_Users;
import com.example.bookingcom.service.Voted_UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Voted_UsersServiceImpl implements Voted_UsersService {

    private final Voted_UsersRepository voted_usersRepository;


    @Override
    public List<Voted_Users> getAllVoted_Users() {
        List<Voted_Users> voted_users=voted_usersRepository.findAll();
        return voted_users;
    }

    @Override
    public void saveVoted_User(Voted_Users voted_user) {
        voted_usersRepository.save(voted_user);
    }
}
