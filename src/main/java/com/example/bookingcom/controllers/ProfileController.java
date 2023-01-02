package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.entities.*;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final CountryService countryService;
    private final PublicationService publicationService;
    private final Publication_TypeService publication_typeService;
    private final Publication_Type_Repository publication_Type_Repository;
    private RoleService roleService;
    private final HotelService hotelService;
    private final TypeOfNomerService typeOfNomerService;
    private final TypeOfHotelService typeOfHotelService;
    private final CityService cityService;
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

    @GetMapping(value = "/message")
    public String message(){
        return "pageForSocket";
    }

    @GetMapping(value = "/cabinet")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String cabinet(Model model){

        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());

        List<Publication> publicationList=publicationService.getAllPublication();
        List<Publication> publications=new ArrayList<>();
        for (Publication t : publicationList){
            if (t.getUser() == user1){
                publications.add(t);
            }
        }


        model.addAttribute("publications",publications);
        model.addAttribute("user",user1);
        return "cabinet";
    }

//    @GetMapping(value = "/payment")
//    public String payment(Model model){
//        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Users user1=userService.getUserByEmail(user.getEmail());
//
//        List<Publication> publicationList=publicationService.getAllPublication();
//        List<Publication> publications=new ArrayList<>();
//        for (Publication t : publicationList){
//            if (t.getUser() == user1){
//                publications.add(t);
//            }
//        }
//
//
//        model.addAttribute("publications",publications);
//        return "payment";
//    }

    @GetMapping(value = "/editprofile")
    public String edit(Model model){
        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());
        List<Country> countries=countryService.getAllCountry();

        List<Publication> publicationList=publicationService.getAllPublication();
        List<Publication> publications=new ArrayList<>();
        for (Publication t : publicationList){
            if (t.getUser() == user1){
                publications.add(t);
            }
        }



        model.addAttribute("publications",publications);

        model.addAttribute("user",user1);
        model.addAttribute("countries",countries);
        return "editprofile";
    }

    @GetMapping(value = "/mybooks")
    public String mybooks(Model model){
        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());
//        List<BooksOfUser> books=user1.getBooksOfUsers();           Здесь я изменил связь убрал с юзера и бобавил в букс оф юзер
//
//        model.addAttribute("books",books);
        return "mybooks";
    }

    @GetMapping(value = "/notification")
    public String notification(Model model){
        List<Publication_Type> getAllPT=publication_typeService.getAllTypes();
        model.addAttribute("types",getAllPT);


        return "notificationPage";
    }

    @PostMapping(value = "/sendNotification")
    public String send(@RequestParam(name = "title") String title,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "url") String url,
                       @RequestParam(name="type") Long typeId,
                       @RequestParam(name = "message") String message){

        Publication_Type type=publication_typeService.findById(typeId);
        List<Publication_Type> types=new ArrayList<>();
        types.add(type);

        List<Users> allUsers=userService.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            publicationService.savePublication(new Publication(null,title,message,url,allUsers.get(i),types));
        }

        return "redirect:/cabinet";
    }

    @GetMapping(value = "/notifPage")
    public String notifPage(Model model){

        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());

        List<Publication_Type> publication_types=publication_typeService.getAllTypes();

        List<Publication> publicationList=publicationService.getAllPublication();
        List<Publication> publications=new ArrayList<>();
        for (Publication t : publicationList){
            if (t.getUser() == user1){
                publications.add(t);
            }
        }

        model.addAttribute("types",publication_types);
        model.addAttribute("publications",publications);
        return "notifPage";
    }

    @GetMapping(value = "/manageUsers")
    public String manageUsers(Model model){
        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());

        List<Users> getAllUsers=userService.getAllUsers();


        model.addAttribute("me",user1);
        model.addAttribute("users",getAllUsers);
        return "manageUsers";
    }

    @PostMapping(value = "/changerole")
    public String changeRole(@RequestParam(name = "role") int role,
                             @RequestParam(name = "usersId") long id,
                             Model model){
        System.out.println(role+"   qwqw");
        System.out.println(id+"   eeee");

        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());

        Optional<Users> user3=userService.getUserById(id);



        System.out.println(user3.get().getName()+"   2323");


        return "redirect:/manageUsers";
    }

    @PostMapping(value = "searchuser")
    private String searchUser(@RequestParam(name = "name") String name,
                              Model model){

        System.out.println(name+"   wwww");

        Users user2=userService.getUserByName(name);

        System.out.println(user2.getName()+"   qqqqq");

            Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users user1=userService.getUserByEmail(user0.getEmail());
            model.addAttribute("me",user1);
            model.addAttribute("users",user2);
            return "redirect:/manageUsers.html";


    }

    @GetMapping(value = "/advertise")
    private String advertise(Model model){

        return "advertise";
    }



    @GetMapping(value = "/myproperties")
    public String myProperties(Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> allHotels=hotelService.getAllHotels();
        List<Country> countries=countryService.getAllCountry();
        List<Cities> cities=cityService.getAllCities();
        List<TypeOfHotel> hotels=typeOfHotelService.getAllTypesOfHotel();
        List<TypeOfNomer> nomers=typeOfNomerService.getAllNomer();
        List<ComfortsOfHotel> comforts=comfortsService.getAllComfortsOfHotel();

        List<Hotels> usersHotels=new ArrayList<>();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getOwner().getId() == user1.getId()){
                usersHotels.add(allHotels.get(i));
            }
        }

        model.addAttribute("countries",countries);
        model.addAttribute("cities",cities);
        model.addAttribute("typeOfHotels",hotels);
        model.addAttribute("typeOfNomers",nomers);
        model.addAttribute("stars",comforts);
        model.addAttribute("hotels",usersHotels);

        return "myproperties";
    }

    @GetMapping(value="/addproperty")
    public String addProperty(Model model){
        return "addproperty";
    }

    @PostMapping(value = "/addingproperty")
    public String addingProperty(Model model){
        return "redirect:/";
    }

    @GetMapping(value = "/manageProperties")
    public String manageProperties(Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

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

        return "manageProperties";
    }







}
