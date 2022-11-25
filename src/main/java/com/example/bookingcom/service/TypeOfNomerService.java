package com.example.bookingcom.service;

import com.example.bookingcom.entities.TypeOfNomer;

import java.util.List;

public interface TypeOfNomerService {

    List<TypeOfNomer> getAllNomer();

    TypeOfNomer getTypeOfNomerById(Long id);

}
