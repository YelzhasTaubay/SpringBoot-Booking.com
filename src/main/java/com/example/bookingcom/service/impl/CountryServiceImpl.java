package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.CountryRepository;
import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.entities.Country;
import com.example.bookingcom.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country getCountryByName(String city) {
        Country country=countryRepository.getCountriesByName(city);
    return country;
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> getAllCountries=countryRepository.findAll();
        return getAllCountries;
    }

    @Override
    public Country getCountriesById(Long id) {
        Country country=countryRepository.getCountriesById(id);
        return country;
    }

    @Override
    public Country getCountriesByCities(Cities cities) {
        Country getCountryByCity=countryRepository.getCountriesByCities(cities);
        return getCountryByCity;
    }
}
