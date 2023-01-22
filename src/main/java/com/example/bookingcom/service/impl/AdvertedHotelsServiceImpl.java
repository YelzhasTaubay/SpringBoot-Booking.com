package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.AdvertedHotelsRepository;
import com.example.bookingcom.entities.AdvertedHotels;
import com.example.bookingcom.service.AdvertedHotelsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertedHotelsServiceImpl implements AdvertedHotelsService {

    private final AdvertedHotelsRepository advertedHotelsRepository;

    @Override
    public List<AdvertedHotels> getAll() {
        List<AdvertedHotels> getAll=advertedHotelsRepository.findAll();
        return getAll;
    }

    @Override
    public void saveAdvertedHotel(AdvertedHotels advertedHotel) {
        advertedHotelsRepository.save(advertedHotel);
    }


}
