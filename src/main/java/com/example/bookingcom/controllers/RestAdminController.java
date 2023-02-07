package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.dao.TypeOfHotelRepository;
import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.dto.UsersDto;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RestAdminController {

    private final HotelService hotelService;
    private final UserService userService;
    private final CountryService countryService;
    private final PublicationService publicationService;
    private final Publication_TypeService publication_typeService;
    private final Publication_Type_Repository publication_Type_Repository;
    private RoleService roleService;
    @Autowired
    private final BookedNomersService bookedNomersServicel;
    @Autowired
    private final TypeOfNomerService typeOfNomerService;
    @Autowired
    private TypeOfHotelRepository typeOfHotelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelsRepository hotelsRepository;
    private final Voted_UsersService voted_usersService;
    private final CityService cityService;

//    @GetMapping( "/allUsers")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
//    public List<UsersDto> allHotels(){
//        return userService.;
//    }
//
//    @GetMapping("/user/{id}")
//    public Optional<UsersDto> user(@PathVariable(name = "id") Long id){
//        return userService.getUserById(id);
//    }





}
