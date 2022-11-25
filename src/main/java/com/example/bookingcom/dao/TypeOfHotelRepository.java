package com.example.bookingcom.dao;


import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.entities.TypeOfHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeOfHotelRepository  extends JpaRepository<TypeOfHotel,Long> {

    TypeOfHotel getTypeOfHotelById(Long id);

}
