package com.example.bookingcom.dto;

import com.example.bookingcom.entities.Country;
import com.example.bookingcom.entities.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {


    private Long id;
    private String name;
    private String surname;
    private int age;
    private Gender gender;
    private String passwordId;
    private String phoneNumber;
    private String email;
    private Country citizenship;

}
