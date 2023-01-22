package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HotelsRepository extends JpaRepository<Hotels,Long> {

    List<Hotels> getHotelsByTitle(String name);

   Hotels findByTitleContaining(String name);

   Hotels findByTitle(String name);

   Hotels findHotelsByTitle(String name);





}
