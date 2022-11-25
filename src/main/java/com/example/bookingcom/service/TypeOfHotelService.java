package com.example.bookingcom.service;

import com.example.bookingcom.entities.TypeOfHotel;

import java.util.List;

public interface TypeOfHotelService {

    List<TypeOfHotel> getAllTypesOfHotel();

    TypeOfHotel getTypeOfHotelById(Long id);

}
