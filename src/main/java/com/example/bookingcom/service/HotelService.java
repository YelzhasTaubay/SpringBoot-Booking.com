package com.example.bookingcom.service;

import com.example.bookingcom.dto.HotelsDto;
import com.example.bookingcom.entities.Hotels;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<Hotels> getAllHotels();

    Optional<Hotels> findHotelById(Long id);

    List<Hotels> getHotelsByName(String name);

    void saveOrUpdateHotel(Hotels hotel);

    Hotels findHotelbyName(String name);

    Hotels getHotelbyIdbyIteration(Long id);

    void deleteHotel(Long id);


//    ======================

    HotelsDto toHotelsDto(Hotels hotel);

    Hotels toHotelFromDto(HotelsDto hotelsDto);

    List<HotelsDto> toHotelsListDto(List<Hotels> hotels);

    List<Hotels> toHotelsListFromDto(List<HotelsDto> hotelsDtos);



}
