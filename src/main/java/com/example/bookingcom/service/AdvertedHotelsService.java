package com.example.bookingcom.service;

import com.example.bookingcom.entities.AdvertedHotels;
import com.example.bookingcom.entities.Hotels;

import java.util.List;

public interface AdvertedHotelsService {

    List<AdvertedHotels> getAll();

    void saveAdvertedHotel(AdvertedHotels advertedHotel);

}
