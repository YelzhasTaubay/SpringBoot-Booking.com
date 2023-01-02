package com.example.bookingcom.service.impl;


import com.example.bookingcom.dao.RoleRepository;
import com.example.bookingcom.entities.Role;
import com.example.bookingcom.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles=roleRepository.findAll();
        return roles;
    }

}
