package com.example.bookingcom.mapper;

import com.example.bookingcom.dto.UsersDto;
import com.example.bookingcom.entities.Users;
import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsersDto getUserDtoFromUser(Users user);

    Users getUserFromUserDto(UsersDto usersDto);

    List<Users> getUsersListFromDtosList(List<UsersDto> usersDtos);

    List<UsersDto> getDtosListFromUsersList(List<Users> users);

}
