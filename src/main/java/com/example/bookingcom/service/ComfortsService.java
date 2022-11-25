package com.example.bookingcom.service;

import com.example.bookingcom.entities.ComfortsOfHotel;

import java.util.List;

public interface ComfortsService {

    List<ComfortsOfHotel> getAllComfortsOfHotel();

    ComfortsOfHotel getComfortsOfHotelById(Long id);

}
