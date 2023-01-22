package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.BookedNomersRepository;
import com.example.bookingcom.entities.BookedNomer;
import com.example.bookingcom.service.BookedNomersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookedNomersServiceImpl implements BookedNomersService {

    @Autowired
    private final BookedNomersRepository bookedNomersRepository;

    @Override
    public List<BookedNomer> findAll() {
        List<BookedNomer> all=bookedNomersRepository.findAll();
        return all;
    }

    @Override
    public void addBookedNomer(BookedNomer bookedNomer) {
        bookedNomersRepository.save(bookedNomer);
    }
}
