package com.example.bookingcom.dao;

import com.example.bookingcom.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findAll();
}
