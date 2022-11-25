package com.example.bookingcom.service;

import com.example.bookingcom.entities.Hotels;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<Hotels> getAllHotels();

    Optional<Hotels> findHotelById(Long id);

    List<Hotels> getHotelsByName(String name);



}
