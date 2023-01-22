package com.example.bookingcom.service;

import com.example.bookingcom.entities.Publication;
import com.example.bookingcom.entities.Publication_Type;

import java.util.List;

public interface PublicationService {

    List<Publication> getAllPublication();

    void savePublication(Publication publication);

    void deletePublication(Publication publication);



}
