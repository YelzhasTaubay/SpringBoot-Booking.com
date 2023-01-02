package com.example.bookingcom.service.impl;

import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.entities.Publication_Type;
import com.example.bookingcom.service.Publication_TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Publication_Type_ServiceImpl implements Publication_TypeService {


    private final Publication_Type_Repository publication_Type_Repository;

    @Override
    public List<Publication_Type> getAllTypes() {
        List<Publication_Type> getAll=publication_Type_Repository.findAll();
        return getAll;
    }

    @Override
    public Publication_Type findById(long id) {
        Publication_Type type=publication_Type_Repository.findById(id);
        return type;
    }


}
