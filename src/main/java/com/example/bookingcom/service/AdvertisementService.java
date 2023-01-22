package com.example.bookingcom.service;

import com.example.bookingcom.entities.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {

    List<Advertisement> getAll();

    Optional<Advertisement> getAdvertisementById(long id);

}
