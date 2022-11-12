package com.example.bookingcom.dao;

import com.example.bookingcom.entities.ComfortsOfHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComfortsRepository extends JpaRepository<ComfortsOfHotel,Long> {

}
