package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.ComfortsRepository;
import com.example.bookingcom.entities.ComfortsOfHotel;
import com.example.bookingcom.service.ComfortsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComfortsServiceImpl implements ComfortsService {

    private final ComfortsRepository comfortsRepository;


    @Override
    public List<ComfortsOfHotel> getAllComfortsOfHotel() {
        List<ComfortsOfHotel> comforts=comfortsRepository.findAll();
        return comforts;
    }

    @Override
    public ComfortsOfHotel getComfortsOfHotelById(Long id) {
        ComfortsOfHotel comfort=comfortsRepository.getComfortsOfHotelById(id);
        return comfort;
    }
}
