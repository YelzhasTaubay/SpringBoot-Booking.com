package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.TypeOfNomerRepository;
import com.example.bookingcom.entities.TypeOfNomer;
import com.example.bookingcom.service.TypeOfHotelService;
import com.example.bookingcom.service.TypeOfNomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfNomerServiceImpl implements TypeOfNomerService {

    private final TypeOfNomerRepository typeOfNomerRepository;

    @Override
    public List<TypeOfNomer> getAllNomer() {
        List<TypeOfNomer> types=typeOfNomerRepository.findAll();
        return types;
    }

    @Override
    public TypeOfNomer getTypeOfNomerById(Long id) {
        TypeOfNomer type=typeOfNomerRepository.getTypeOfNomerById(id);
        return type;
    }
}
