package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelsRepository hotelsRepository;

    @Override
    public List<Hotels> getAllHotels() {
        List<Hotels> allHotels=hotelsRepository.findAll();
        return allHotels;
    }

    @Override
    public Optional<Hotels> findHotelById(Long id) {
        Optional<Hotels> hotel=hotelsRepository.findById(id);
        return hotel;
    }

    @Override
    public List<Hotels> getHotelsByName(String name) {
        List<Hotels> hotel=hotelsRepository.getHotelsByTitle(name);
        return hotel;
    }
}
