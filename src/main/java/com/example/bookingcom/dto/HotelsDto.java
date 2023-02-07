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
    @Column(columnDefinition = "Text")
    private String description;
    private String street;
    @Column(nullable = true,updatable = true,insertable = true)
    private double rating;
    @ManyToOne
    private UsersDto usersDto;
    @ManyToMany
    private List<Country> country;
    @OneToOne
    private TypeOfHotel typeOfHotel;
    @ManyToMany
    private List<ComfortsOfHotel> comforts;
    @ManyToMany
    List<StuffsOfHotel> stuffsOfHotels;
    @OneToMany
    private List<PhotosOfHotel> photos;


}
