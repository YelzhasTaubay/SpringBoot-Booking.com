package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users,Long> {


    Users findByEmail(String email);

    Users findUsersByName(String name);



}
