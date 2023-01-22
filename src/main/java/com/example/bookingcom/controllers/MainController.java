package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.entities.*;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;

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
    private final AdvertisementService advertisementService;
    private final AdvertedHotelsService advertedHotelsService;
    private final Voted_UsersService voted_usersService;
    private final HotelsRepository hotelsRepository;
    private final PasswordEncoder passwordEncoder;
    private  final RoleService roleService;

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
        List<AdvertedHotels> advertedHotels=advertedHotelsService.getAll();
        List<Hotels> allHotels=hotelService.getAllHotels();
        List<Country> countries=countryService.getAllCountry();
        List<Cities> cities=cityService.getAllCities();
        List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
        List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
        List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();

        model.addAttribute("advertedHotels",advertedHotels);
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

        System.out.println(hotelsTitle+" | "+cityId+" | "+hotelsTypeId+" | "+starsId+" | "+nomersId);

        Hotels foundHotel=null;
//        List<Hotels> allHotels=hotelService.getAllHotels();
       if (hotelsTitle==null && cityId==null && hotelsTypeId==null && starsId==null && nomersId==null){
           List<AdvertedHotels> advertedHotels=advertedHotelsService.getAll();
           List<Hotels> allHotels1=hotelService.getAllHotels();
           List<Country> countries=countryService.getAllCountry();
           List<Cities> cities=cityService.getAllCities();
           List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
           List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
           List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();

           model.addAttribute("advertedHotels",advertedHotels);
           model.addAttribute("countries",countries);
           model.addAttribute("cities",cities);
           model.addAttribute("typeOfHotels",hotels);
           model.addAttribute("typeOfNomers",nomers);
           model.addAttribute("stars",comforts);
           model.addAttribute("hotels",allHotels1);

           return "redirect:/";

       }else if (hotelsTitle!="1" && cityId.equals("1") && hotelsTypeId.equals("1") && starsId.equals("1") && nomersId.equals("1") ){

                foundHotel=hotelService.findHotelbyName(hotelsTitle);
           System.out.println(foundHotel.getTitle()+"    |||   "+foundHotel.getRating());

                if (foundHotel != null){
                    model.addAttribute("hotels",foundHotel);
                }else {
                                    System.out.println("2222");
                List<Hotels> allHotels1=hotelService.getAllHotels();
                model.addAttribute("hotels", allHotels1);
                }

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
        return "redirect:/";
    }


    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model){
//        Optional<Hotels> hotel=hotelService.findHotelById(id);
        List<Hotels> hotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (Hotels h: hotels){
            if (h.getId() == id){
                hotel=h;
            }
        }

        List<PhotosOfHotel> photos=hotel.getPhotos();
        List<Comments> allComments=commentsService.getAllComments();

        List<Comments> allComments1=new ArrayList<>();
        for (Comments c: allComments){
            if (c.getHotel().getId() == id){
                allComments1.add(c);
            }
        }

        List<Voted_Users> voted_users=voted_usersService.getAllVoted_Users();
        List<Voted_Users> hotelsVotedUsers=new ArrayList<>();
        for (int i = 0; i < voted_users.size(); i++) {
            if (voted_users.get(i).getHotel().getId() == id){
                hotelsVotedUsers.add(voted_users.get(i));
            }
        }

        model.addAttribute("votedUsers",hotelsVotedUsers);
        model.addAttribute("comments",allComments1);
        model.addAttribute("hotel",hotel);
        model.addAttribute("photos",photos);
        return "details";
    }

    @GetMapping(value = "/commenting")
    public String commenting(@RequestParam(name = "hotelsId") long id,
                             @RequestParam(name = "comment") String comment,
                             Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

//        Optional<Hotels> hotel=hotelService.findHotelById(id);
        List<Hotels> hotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (Hotels h: hotels){
            if (h.getId() == id){
                hotel=h;
            }
        }

        List<PhotosOfHotel> photos=hotel.getPhotos();

        commentsService.addComment(new Comments(null,comment,now(),user1,hotel));

        List<Comments> allComments=commentsService.getAllComments();

        List<Comments> allComments1=new ArrayList<>();
        for (Comments c: allComments){
            if (c.getHotel().getId() == id){
                allComments1.add(c);
            }
        }

        model.addAttribute("comments",allComments1);
        model.addAttribute("photos",photos);
        model.addAttribute("hotel",hotel);
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

    @PostMapping(value = "/signin")
    public String signin(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "boolean") boolean type){

        System.out.println(email+" | "+ password+" | "+type);

        return "redirect:/";
    }

    @PostMapping(value = "/registrationing")
    public String registrationing(@RequestParam(name = "name") String name,
                                  @RequestParam(name = "surname") String surname,
                                  @RequestParam(name = "email") String email,
                                  @RequestParam(name = "password") String password,
                                  @RequestParam(name = "password2") String password2,
                                  Model model){

        String pass= passwordEncoder.encode(password);
        Role role=roleService.getAllRoles().get(1);
        List<Role> roles=new ArrayList<>();
        roles.add(role);

        Users user=new Users(null,name,surname,0,null,null,null,email,pass,roles,null,null,null,null,null);
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
