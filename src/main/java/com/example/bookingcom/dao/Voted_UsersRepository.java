package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Voted_Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface Voted_UsersRepository extends JpaRepository<Voted_Users,Long> {

}
