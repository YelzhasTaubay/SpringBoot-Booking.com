package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.CityRepository;
import com.example.bookingcom.dto.CityDTO;
import com.example.bookingcom.dto.mapper.CityMapper;
import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.entities.ComfortsOfHotel;
import com.example.bookingcom.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;


    @Override
    public List<Cities> getAllCities() {
        List<Cities> getAllCities=cityRepository.findAll();
        return getAllCities;
    }

    @Override
    public Cities getCity(String city) {
        Cities cityy=cityRepository.getCitiesByName(city);
        return cityy;
    }

    @Override
    public Cities getCitiesById(Long id) {
        Cities city=cityRepository.getCitiesById(id);
        return city;
    }


}
