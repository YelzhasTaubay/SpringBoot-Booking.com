package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.dto.HotelsDto;
import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.mapper.HotelMapper;
import com.example.bookingcom.mapper.HotelMapperImpl;
import com.example.bookingcom.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelsRepository hotelsRepository;
    private final HotelMapper hotelMapper;

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

    @Override
    public void saveOrUpdateHotel(Hotels hotel) {
        hotelsRepository.save(hotel);
    }

    @Override
    public Hotels findHotelbyName(String name) {
        Hotels hotel=hotelsRepository.findHotelsByTitle(name);
        return hotel;
    }

    @Override
    public void deleteHotel(Long id) {
        hotelsRepository.deleteById(id);
    }

    @Override
    public HotelsDto toHotelsDto(Hotels hotel) {
        return hotelMapper.toHotelsDto(hotel);
    }

    @Override
    public Hotels toHotelFromDto(HotelsDto hotelsDto) {
        return hotelMapper.toHotelFromDto(hotelsDto);
    }

    @Override
    public List<HotelsDto> toHotelsListDto(List<Hotels> hotels) {
        return hotelMapper.toHotelsListDto(hotels);
    }

    @Override
    public List<Hotels> toHotelsListFromDto(List<HotelsDto> hotelsDtos) {
        return hotelMapper.toHotelsListFromDto(hotelsDtos);
    }

    public Hotels getHotelbyIdbyIteration(Long id){
        Hotels hotel=null;
        for (int i = 0; i < getAllHotels().size(); i++) {
            if (getAllHotels().get(i).getId() == id){
                hotel=getAllHotels().get(i);
            }
        }
        return hotel;
    }




}
