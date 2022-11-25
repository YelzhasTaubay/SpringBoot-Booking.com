package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.entities.TypeOfNomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfNomerRepository extends JpaRepository<TypeOfNomer,Long> {

    TypeOfNomer getTypeOfNomerById(Long id);

}
