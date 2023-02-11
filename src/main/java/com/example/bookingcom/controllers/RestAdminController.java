package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.dao.TypeOfHotelRepository;
import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.dto.HotelsDto;
import com.example.bookingcom.dto.UsersDto;
import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping( "/allUsers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<UsersDto> allUsers(){
        return userService.getDtosListFromUsersList(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public UsersDto user(@PathVariable(name = "id") Long id){
        return userService.getUserDtoFromUser(userService.getUserByIdFromIteration(id));
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody Users user){
        userService.addUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(name="id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/allHotels")
    public List<HotelsDto> allHotels(){
        return hotelService.toHotelsListDto(hotelService.getAllHotels());
    }

    @GetMapping("/hotel/{id}")
    public HotelsDto hotel(@PathVariable(name = "id") Long id){
        return hotelService.toHotelsDto(hotelService.getHotelbyIdbyIteration(id));
    }

    @PostMapping("/addHotel")
    public void addHotel(@RequestBody Hotels hotel){
        hotelService.saveOrUpdateHotel(hotel);
    }

    @DeleteMapping("/hotel/{id}")
    public void deleteHotel(@PathVariable(name = "id") Long id){
        hotelService.deleteHotel(id);
    }

}
