package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.dao.RoleRepository;
import com.example.bookingcom.entities.*;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final CountryService countryService;
    @Autowired
    private final PublicationService publicationService;
    private final Publication_TypeService publication_typeService;
    private final Publication_Type_Repository publication_Type_Repository;
    private final RoleService roleService;
    private final HotelService hotelService;
    private final TypeOfNomerService typeOfNomerService;
    private final TypeOfHotelService typeOfHotelService;
    private final CityService cityService;
    private final ComfortsService comfortsService;

    private final BookedNomersService bookedNomersService;
    private final AdvertisementService advertisementService;
    private final AdvertedHotelsService advertedHotelsService;
    @Autowired
    private RoleRepository roleRepository;


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
        if (user1.getAge() == 0){
            return "/editprofile2";
        }else {
            return "cabinet";
        }
    }

    @GetMapping(value = "/editingprofile")
    public String editingProfile(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "surname") String surname,
                                 @RequestParam(name = "phone") String phone,
                                 @RequestParam(name = "citizenship") String citizenship,
                                 @RequestParam(name = "passwordId") String passwordId,
                                 @RequestParam(name = "male") String male,
                                 @RequestParam(name = "female") String female,
                                 @RequestParam(name = "day") String day,
                                 @RequestParam(name = "month") String month,
                                 @RequestParam(name = "year") String year,
                                 @RequestParam(name = "numberOfCard") long numberOfCard,
                                 @RequestParam(name = "valid") String valid,
                                 @RequestParam(name = "money") int money,
                                 Model model){

        System.out.println(name+"    "+surname+"    "+phone+"   "+citizenship+"     "+passwordId+"  "+
                male+"    "+female+"    "+ day+ " "+month+" "+year+" "+numberOfCard+" "+valid+" "+money);

        return "redirect:/";
    }

    @GetMapping(value = "/editprofile2")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String editProfile(Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        model.addAttribute("user",user1);
        return "editprofile2";
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

        List<Publication> publicationList=publicationService.getAllPublication();
        List<Publication> publications=new ArrayList<>();
        for (Publication t : publicationList){
            if (t.getUser() == user1){
                publications.add(t);
            }
        }


        List<BookedNomer> bookedNomerList=bookedNomersService.findAll();
        ArrayList<BookedNomer> myBooks=new ArrayList<>();
        for (BookedNomer b: bookedNomerList){
            if (b.getUser() == user1){
                myBooks.add(b);
            }
        }

        model.addAttribute("publications",publications);
        model.addAttribute("books",myBooks);
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

    @GetMapping(value = "/readmore")
    private String readmore(@RequestParam(name = "title") String title,
                            @RequestParam(name = "type") String type,
                            @RequestParam(name = "body") String body,
                            Model model){

        System.out.println(title+" | "+type+" | "+body);


        model.addAttribute("title",title);
        model.addAttribute("type",type);
        model.addAttribute("body",body);
        return "readMoreNotif";
    }


    @GetMapping(value="/alreadyRead")
    public String alreadyRead(@RequestParam(name = "id") long id){

        List<Publication> publicationList=publicationService.getAllPublication();

        Publication publication=new Publication();
        for (Publication p: publicationList){
            System.out.println(p.getTitle()+" |||");
            if (p.getId() == id){
                publication=p;
            }
        }

        publicationService.deletePublication(publication);

        return "redirect:/notifPage";
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String changeRole(@RequestParam(name = "role") long role,
                             @RequestParam(name = "usersId") long usersId,
                             Model model){
        Users user= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user.getEmail());

        System.out.println(role+"   Id of Role");
        System.out.println(usersId+"   Id Users");

//        Optional<Users> user3=userService.getUserById(usersId);

        List<Users> usersList=userService.getAllUsers();
        Users user4=new Users();
        for (Users u: usersList){
            if (u.getId() == usersId){
                user4=u;
            }
        }

        System.out.println(user4.getName()+"  ||");

        List<Role> roles=roleService.getAllRoles();
        List<Role> changedRoles=new ArrayList<>();
        for (Role r: roles){
            if (r.getId() == role) {
                changedRoles.add(r);
            }
        }

        user4.setRoles(changedRoles);
        userService.updateUser(user4);


        return "redirect:/manageUsers";
    }

    @PostMapping(value = "searchuser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    private String searchUser(@RequestParam(name = "name") String name,
                              Model model){



        System.out.println(name+"   wwww");

        List<Users> usersList=userService.getAllUsers();
        Users user2=new Users();
        for (Users u: usersList){
            if (u.getName().equals(name)){
                user2=u;
            }
        }

        System.out.println(user2.getName()+"   qqqqq");

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

            model.addAttribute("me",user1);
            model.addAttribute("users",user2);
            return "redirect:/manageUsers.html";


    }

    @GetMapping(value = "/advertise")
    public String advertise(Model model){

        List<Advertisement> advertisementList = advertisementService.getAll();
        model.addAttribute("advertise", advertisementList);

        return "advertise";
    }

    @GetMapping(value = "/addAdvertise")
    public String addAdvertise(@RequestParam(name = "id") long id,
                               Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Advertisement> advertisementList=advertisementService.getAll();
        Advertisement advertisement=new Advertisement();
        for (Advertisement a: advertisementList){
            if (a.getId() == id){
                advertisement=a;
            }
        }

        System.out.println(advertisement.getType()+"   "+ advertisement.getPrice());

        List<Hotels> getAllHotels=hotelService.getAllHotels();
        List<Hotels> myHotels=new ArrayList<>();
        for (Hotels h: getAllHotels){
            if (h.getOwner().getId() == user1.getId()){
                myHotels.add(h);
            }
        }


        model.addAttribute("advretisement",advertisement);
        model.addAttribute("myHotels",myHotels);
        return "myHotelToAdvert";
    }

    @GetMapping(value = "/advertising")
    public  String advertising(@RequestParam(name = "idOfAdvertise") long idOfAdvertise,
                               @RequestParam(name = "idOfHotel") long idOfHotel,
                               Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        System.out.println(idOfAdvertise+"   ||  "+idOfHotel);

        List<Hotels> getAllHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (Hotels h: getAllHotels){
            if (h.getId() == idOfHotel){
                hotel=h;
            }
        }

        List<Advertisement> advertisementList=advertisementService.getAll();
        Advertisement advertisement=new Advertisement();
        for (Advertisement a: advertisementList){
            if (a.getId() == idOfAdvertise){
                advertisement=a;
            }
        }


        String date= String.valueOf(LocalDate.now());

        model.addAttribute("advertisement",advertisement);
        model.addAttribute("date",date);
        model.addAttribute("idOfAdvertise",idOfAdvertise);
        model.addAttribute("user",user1);
        model.addAttribute("hotelDetails",hotel.getOwner().getBank_card());
        model.addAttribute("hotelsId",idOfHotel);
        return "advertisingPage";
    }

    @GetMapping(value = "/adverting")
    public String adverting(@RequestParam(name = "idOfAdvertise") long idOfAdvertise,
                            @RequestParam(name = "hotelsId") long idOfHotel,
                            Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> getAllHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (Hotels h: getAllHotels){
            if (h.getId() == idOfHotel){
                hotel=h;
            }
        }

        List<Advertisement> advertisementList=advertisementService.getAll();
        Advertisement advertisement=new Advertisement();
        for (Advertisement a: advertisementList){
            if (a.getId() == idOfAdvertise){
                advertisement=a;
            }
        }

        int remindMoney=user1.getBank_card().getMoneyInAccount()-advertisement.getPrice();
        user1.getBank_card().setMoneyInAccount(remindMoney);

        advertedHotelsService.saveAdvertedHotel(new AdvertedHotels(null,hotel,advertisement));

        return "redirect:/cabinet";
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
