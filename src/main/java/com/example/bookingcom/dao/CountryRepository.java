package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {

    Country getCountriesByName(String name);

    Country getCountriesById(Long id);

    Country getCountriesByCities(Cities cities);

}
