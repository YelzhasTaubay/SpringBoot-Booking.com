package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {

}
