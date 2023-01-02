package com.example.bookingcom.controllers;

import com.example.bookingcom.entities.*;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CityService cityService;
    private final CountryService countryService;
    private final UserService userService;
    private final HotelService hotelService;
    private final CommentsService commentsService;
    private final TypeOfHotelService typeOfHotelService;
    private final TypeOfNomerService typeOfNomerService;
    private final ComfortsService comfortsService;

    public Users getUserData(){
        Users user=null;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User ud= (User) authentication.getPrincipal();
            user=userService.getUserByEmail(ud.getUsername());
        }
        return user;
    }

    @GetMapping(value = {"","/"})
    public String mainMenu(Model model){
        List<Hotels> allHotels=hotelService.getAllHotels();
        List<Country> countries=countryService.getAllCountry();
        List<Cities> cities=cityService.getAllCities();
        List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
        List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
        List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();

        model.addAttribute("countries",countries);
        model.addAttribute("cities",cities);
        model.addAttribute("typeOfHotels",hotels);
        model.addAttribute("typeOfNomers",nomers);
        model.addAttribute("stars",comforts);
        model.addAttribute("hotels",allHotels);
        return "mainMenu";
    }

    @PostMapping(value = "/search")
    public String search(@RequestParam(name = "hotel",required = false) String hotelsTitle,
                         @RequestParam(name = "city",required = false) String cityId,
                         @RequestParam(name = "type",required = false) String hotelsTypeId,
                         @RequestParam(name = "stars",required = false) String starsId,
                         @RequestParam(name = "nomer",required = false) String nomersId,
                         Model model){



        List<Hotels> foundHotels=null;
        List<Hotels> allHotels=hotelService.getAllHotels();
       if (hotelsTitle == null && cityId == null && hotelsTypeId == null && starsId == null && nomersId == null){
           return "redirect:/";
       }else if (hotelsTitle !=null ){
            foundHotels=hotelService.getHotelsByName(hotelsTitle);
            model.addAttribute("hotels",foundHotels);
           List<Country> countries=countryService.getAllCountry();
           List<Cities> cities=cityService.getAllCities();
           List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
           List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
           List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();


           model.addAttribute("countries",countries);
           model.addAttribute("cities",cities);
           model.addAttribute("typeOfHotels",hotels);
           model.addAttribute("typeOfNomers",nomers);
           model.addAttribute("stars",comforts);

           return "mainMenu.html";

       }else if (hotelsTitle !=null && cityId != null){
            foundHotels=hotelService.getHotelsByName(hotelsTitle);
            Cities city=cityService.getCitiesById(Long.valueOf(cityId));
            Country country=countryService.getCountriesByCities(city);
            List<Hotels> foundHotels2=null;
           for (int i = 0; i < foundHotels.size(); i++) {
               if (foundHotels.get(i).getCountry() == country ){
                   foundHotels2.add((Hotels) foundHotels);
               }

               model.addAttribute("hotels",foundHotels2);
               List<Country> countries=countryService.getAllCountry();
               List<Cities> cities=cityService.getAllCities();
               List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
               List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
               List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();


               model.addAttribute("countries",countries);
               model.addAttribute("cities",cities);
               model.addAttribute("typeOfHotels",hotels);
               model.addAttribute("typeOfNomers",nomers);
               model.addAttribute("stars",comforts);

               return "mainMenu.html";
           }
       }else {
           return "redirect:/";
       }



//        List<Hotels> foundHotels=hotelService.getHotelsByName(hotelsTitle);
//        if (foundHotels!=null){
//            model.addAttribute("hotels",foundHotels);
//        }else {
//            return "redirect:/";
//        }




        List<Country> countries=countryService.getAllCountry();
        List<Cities> cities=cityService.getAllCities();
        List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
        List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
        List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();


        model.addAttribute("countries",countries);
        model.addAttribute("cities",cities);
        model.addAttribute("typeOfHotels",hotels);
        model.addAttribute("typeOfNomers",nomers);
        model.addAttribute("stars",comforts);

        return "redirect:/";
    }


    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model){
        Optional<Hotels> hotel=hotelService.findHotelById(id);
        List<PhotosOfHotel> photos=hotel.get().getPhotos();
        List<Comments> allComments=commentsService.getAllComments();

        model.addAttribute("comments",allComments);
        model.addAttribute("hotel",hotel);
        model.addAttribute("photos",photos);
        return "details";
    }

    @GetMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/registrationing")
    public String registrationing(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname,
                                  @RequestParam(name = "email") String email,
                                  @RequestParam(name = "password") String password,
                                  @RequestParam(name = "password2") String password2){

        Users user=new Users(null,name,surname,0,null,null,null,email,password,null,null,null,null,null,null);
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(name = "cityorcountry") String cityOrCountry,
                         @RequestParam(name = "type") String typeOfHotel,
                         @RequestParam(name = "stars") String stars){

        System.out.println(cityOrCountry);
        System.out.println(typeOfHotel);
        System.out.println(stars);

        return "redirect:/";
    }











}
