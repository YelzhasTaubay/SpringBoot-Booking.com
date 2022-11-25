package com.example.bookingcom.service;

import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.entities.Country;

import java.util.List;

public interface CountryService {

    Country getCountryByName(String city);

    List<Country> getAllCountry();

    Country getCountriesById(Long id);


    Country getCountriesByCities(Cities cities);

}
