package com.example.bookingcom.service;

import com.example.bookingcom.entities.BookedNomer;

import java.util.List;

public interface BookedNomersService {

    List<BookedNomer> findAll();

    void addBookedNomer(BookedNomer bookedNomer);

}
