package com.example.bookingcom.service;

import com.example.bookingcom.dto.CityDTO;
import com.example.bookingcom.entities.Cities;

import java.util.List;

public interface CityService {

    List<Cities> getAllCities();

    List<CityDTO> getCitiesDTO();

}