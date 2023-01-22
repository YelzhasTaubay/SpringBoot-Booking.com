package com.example.bookingcom.dao;

import com.example.bookingcom.entities.BookedNomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedNomersRepository extends JpaRepository<BookedNomer,Long> {

}
