package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Cities,Long> {


}
