package com.example.bookingcom.dto.mapper;


import com.example.bookingcom.dto.UserDTO;
import com.example.bookingcom.entities.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper{

    UserDTO toUserDTO(Users user);
    Users toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<Users> users);
    List<Users> toUserList(List<UserDTO> userDTO);


}
