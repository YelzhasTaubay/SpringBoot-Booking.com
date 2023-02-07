package com.example.bookingcom.service;

import com.example.bookingcom.dto.UsersDto;
import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.entities.Role;
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

    void updateUser(Users user);


    UsersDto getUserDtoFromUser(Users user);

    Users getUserFromUserDto(UsersDto usersDto);

    List<Users> getUsersListFromDtosList(List<UsersDto> usersDtos);

    List<UsersDto> getDtosListFromUsersList(List<Users> users);


}
