package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.CityRepository;
import com.example.bookingcom.dto.CityDTO;
import com.example.bookingcom.dto.mapper.CityMapper;
import com.example.bookingcom.entities.Cities;
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
        return null;
    }


}
