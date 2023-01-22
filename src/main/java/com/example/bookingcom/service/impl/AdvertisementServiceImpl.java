package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.AdvertisementRepository;
import com.example.bookingcom.entities.Advertisement;
import com.example.bookingcom.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;


    @Override
    public List<Advertisement> getAll() {
        List<Advertisement> getAll=advertisementRepository.findAll();
        return getAll;
    }

    @Override
    public Optional<Advertisement> getAdvertisementById(long id) {
        Optional<Advertisement> advertisement=advertisementRepository.findById(id);
        return advertisement;
    }
}
