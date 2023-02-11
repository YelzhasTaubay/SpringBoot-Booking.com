package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.dto.UsersDto;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.mapper.UserMapper;
import com.example.bookingcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users getUserByIdFromIteration(Long id) {
        Users user=null;
        for (int i = 0; i < getAllUsers().size(); i++) {
            if (id == getAllUsers().get(i).getId()){
                user= getAllUsers().get(i);
            }
        }
        return user;
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
    public void updateUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UsersDto getUserDtoFromUser(Users user) {
        return userMapper.getUserDtoFromUser(user);
    }

    @Override
    public Users getUserFromUserDto(UsersDto usersDto) {
        return userMapper.getUserFromUserDto(usersDto);
    }

    @Override
    public List<Users> getUsersListFromDtosList(List<UsersDto> usersDtos) {
        return userMapper.getUsersListFromDtosList(usersDtos);
    }

    @Override
    public List<UsersDto> getDtosListFromUsersList(List<Users> users) {
        return userMapper.getDtosListFromUsersList(users);
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
