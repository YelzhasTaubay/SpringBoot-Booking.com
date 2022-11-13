package com.example.bookingcom.dto.mapper;

import com.example.bookingcom.dto.BooksOfUserDTO;
import com.example.bookingcom.entities.BooksOfUser;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BoooksOfUserMapper {


    BooksOfUserDTO toBooksOfUserDTO(BooksOfUser booksOfUser);
    BooksOfUser toBooksOfUser(BooksOfUserDTO booksOfUserDTO);

    List<BooksOfUserDTO> toBooksOfUserDTO(List<BooksOfUser> booksOfUsers);
    List<BooksOfUser> toBooksOfUser(List<BooksOfUserDTO> booksOfUserDTOS);


}
