package com.example.bookingcom.dto;

import com.example.bookingcom.entities.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class HotelsDto {

    private Long id;
    private String title;
    private String description;
    private String street;
    private double rating;
    private UsersDto owner;
    private List<Country> country;
    private TypeOfHotel typeOfHotel;
    private List<ComfortsOfHotel> comforts;
    List<StuffsOfHotel> stuffsOfHotels;
    private List<PhotosOfHotel> photos;


}
