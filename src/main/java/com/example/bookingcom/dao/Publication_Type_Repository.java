package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Publication_Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Publication_Type_Repository extends JpaRepository<Publication_Type,Long> {

    Publication_Type findById(long id);

}
