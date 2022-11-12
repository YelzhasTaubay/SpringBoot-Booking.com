package com.example.bookingcom.dao;

import com.example.bookingcom.entities.BooksOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksOfUsersRepository extends JpaRepository<BooksOfUser,Long> {
}
