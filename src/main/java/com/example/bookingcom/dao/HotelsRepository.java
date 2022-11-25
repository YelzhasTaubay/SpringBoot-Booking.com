package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelsRepository extends JpaRepository<Hotels,Long> {

    List<Hotels> getHotelsByTitle(String name);



}
