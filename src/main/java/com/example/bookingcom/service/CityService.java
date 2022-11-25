package com.example.bookingcom.service;

import com.example.bookingcom.dto.CityDTO;
import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.entities.ComfortsOfHotel;

import java.util.List;

public interface CityService {

    List<Cities> getAllCities();

    Cities getCity(String city);

    Cities getCitiesById(Long id);



}
