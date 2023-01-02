package com.example.bookingcom.service;

import com.example.bookingcom.entities.Publication_Type;

import java.util.List;
import java.util.Optional;

public interface Publication_TypeService {



    List<Publication_Type> getAllTypes();

    Publication_Type findById(long id);



}
