package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.entities.Hotels;
import com.example.bookingcom.entities.TypeOfNomer;
import com.example.bookingcom.entities.Users;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;
    private final CountryService countryService;
    private final PublicationService publicationService;
    private final Publication_TypeService publication_typeService;
    private final Publication_Type_Repository publication_Type_Repository;
    private RoleService roleService;
    @Autowired
    private HotelService hotelService;



    public Users getUserData(){
        Users user=null;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User ud= (User) authentication.getPrincipal();
            user=userService.getUserByEmail(ud.getUsername());
        }
        return user;
    }

    @GetMapping(value = "/book/{id}")
    public String book(@PathVariable(name="id") Long id,
                        Model model){

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == id){
                hotel=allHotels.get(i);
            }
        }
        List<TypeOfNomer> nomers=hotel.getTypeOfHotel().getTypeOfNomers();

        model.addAttribute("hotelsId",hotel.getId());
        model.addAttribute("nomers",nomers);
        System.out.println(id+ "    www");
        return "book";
    }

    @GetMapping(value = "/booking")
    public String booking(@RequestParam(name = "typeId") long typeId,
                          @RequestParam(name = "hotelsId") long hotelsId,
                          Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        System.out.println(typeId+"   ||||    "+hotelsId);

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == hotelsId){
                hotel=allHotels.get(i);
            }
        }



        return "booking";
    }

    @GetMapping(value = "/favorites")
    public String favorites(Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

//        List<Favorites> favorites=user1.getFavorites();
//
//        model.addAttribute("favorites",favorites);

        model.addAttribute("user",user1);
        return "favorites";
    }

    @GetMapping(value = "/addfavorites")
    private String favorites(@RequestParam(name = "hotelsId") long id,
                             Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> hotelsList=hotelService.getAllHotels();
        Hotels hotel=null;
        for (int i = 0; i < hotelsList.size(); i++) {
            if (hotelsList.get(i).getId() == id){
                hotel=hotelsList.get(i);
            }
        }
        List<Hotels> hotels=new ArrayList<>();
        hotels.add(hotel);
//        List<Favorites> favorites=new ArrayList<>();
//        favorites.add(new Favorites(null,hotels));
//        user1.setFavorites(favorites);

        //System.out.println(user1.getFavorites().get(0).getHotelsList().get(0).getTitle());

        return "redirect:/";
    }










}
