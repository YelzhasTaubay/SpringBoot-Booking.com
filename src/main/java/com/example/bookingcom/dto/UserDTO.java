package com.example.bookingcom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String surname;
    private String passportId;
    private int age;
    private int money;
    private CountryDTO citizenship;
    private List<BooksOfUserDTO> booksOfUsers;


}
