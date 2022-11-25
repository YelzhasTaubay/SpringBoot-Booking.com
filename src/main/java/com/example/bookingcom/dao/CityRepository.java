package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<Cities,Long> {

    Cities getCitiesByName(String name);

    Cities getCitiesById(Long id);


}
