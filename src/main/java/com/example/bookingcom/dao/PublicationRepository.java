package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication,Long> {

}
