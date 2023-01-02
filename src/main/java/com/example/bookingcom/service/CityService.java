package com.example.bookingcom.service;

import com.example.bookingcom.entities.Cities;

import java.util.List;

public interface CityService {

    List<Cities> getAllCities();

    Cities getCity(String city);

    Cities getCitiesById(Long id);



}
