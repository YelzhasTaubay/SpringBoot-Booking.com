package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.PublicationRepository;
import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.entities.Publication;
import com.example.bookingcom.entities.Publication_Type;
import com.example.bookingcom.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;


    @Override
    public List<Publication> getAllPublication() {
        List<Publication> getAll=publicationRepository.findAll();
        return getAll;
    }

    @Override
    public void savePublication(Publication publication) {
        publicationRepository.save(publication);
    }
}
