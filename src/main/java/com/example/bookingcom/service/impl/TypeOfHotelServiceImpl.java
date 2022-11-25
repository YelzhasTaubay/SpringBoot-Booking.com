package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.TypeOfHotelRepository;
import com.example.bookingcom.entities.TypeOfHotel;
import com.example.bookingcom.service.TypeOfHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfHotelServiceImpl implements TypeOfHotelService {

    private final TypeOfHotelRepository typeOfHotelRepository;


    @Override
    public List<TypeOfHotel> getAllTypesOfHotel() {
        List<TypeOfHotel> getallTypes=typeOfHotelRepository.findAll();
        return getallTypes;
    }

    @Override
    public TypeOfHotel getTypeOfHotelById(Long id) {
        TypeOfHotel type=typeOfHotelRepository.getTypeOfHotelById(id);
        return type;
    }
}
