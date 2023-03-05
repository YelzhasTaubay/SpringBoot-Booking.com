package com.example.bookingcom.controllers;

import com.example.bookingcom.dao.HotelsRepository;
import com.example.bookingcom.dao.Publication_Type_Repository;
import com.example.bookingcom.dao.TypeOfHotelRepository;
import com.example.bookingcom.dao.UserRepository;
import com.example.bookingcom.entities.*;
import com.example.bookingcom.service.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;




/**
 *      Я знаю что нельзя писать логику в контроллере это как говорится not reusable, а переписать логику в сервис не хочется.
 *   Если не найду работу с этим проектом то следующий проект буду делать правильным образом как говорится чисто писать код,
 *   так или иначе я многому научился делая этот свой корявый мини пет-проект.
 */





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
                          @RequestParam(name = "priceOfNomer") int priceOfNomer,
                          Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        System.out.println(typeId+" | "+hotelsId+" | "+priceOfNomer);

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == hotelsId){
                hotel=allHotels.get(i);
            }
        }

        List<TypeOfNomer> allNomers=typeOfNomerService.getAllNomer();
        ArrayList<BookedNomer> allBooksOfNomer= (ArrayList<BookedNomer>) bookedNomersServicel.findAll();
        ArrayList<BookedNomer> filtredBooks=new ArrayList<>();
        for (int i = 0; i < allBooksOfNomer.size(); i++) {
            if (allBooksOfNomer.get(i).getHotel().getId() == hotel.getId() ){
                filtredBooks.add(allBooksOfNomer.get(i));
            }
        }

        ArrayList<BookedNomer> lastFilter=new ArrayList<>();
        for (BookedNomer f: filtredBooks){
            if (f.getNomer().getId() == typeId){
                lastFilter.add(f);

            }
        }
        model.addAttribute("priceOfNomer",priceOfNomer);
        model.addAttribute("typeId",typeId);
        model.addAttribute("hotelsId",hotelsId);
        model.addAttribute("books",lastFilter);
        return "booking";
    }

    @GetMapping(value = "/checkDate")
    private String checkDate(@RequestParam(name = "date") String date,
                             @RequestParam(name = "typeId") long typeId,
                             @RequestParam(name = "hotelsId") long hotelsId,
                             @RequestParam(name = "priceOfNomer") int priceOfNomer,
                             Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        System.out.println(typeId+"   ||||    "+hotelsId+" | "+date+" | "+priceOfNomer);

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == hotelsId){
                hotel=allHotels.get(i);
            }
        }

        List<TypeOfNomer> allNomers=typeOfNomerService.getAllNomer();
        ArrayList<BookedNomer> allBooksOfNomer= (ArrayList<BookedNomer>) bookedNomersServicel.findAll();
        ArrayList<BookedNomer> filtredBooks=new ArrayList<>();
        for (int i = 0; i < allBooksOfNomer.size(); i++) {
            if (allBooksOfNomer.get(i).getHotel().getId() == hotel.getId() ){
                filtredBooks.add(allBooksOfNomer.get(i));
            }
        }

        ArrayList<BookedNomer> lastFilter=new ArrayList<>();
        for (BookedNomer f: filtredBooks){
            if (f.getNomer().getId() == typeId){
                lastFilter.add(f);
            }
        }

        model.addAttribute("priceOfNomer", priceOfNomer);
        model.addAttribute("date",date);
        model.addAttribute("typeId",typeId);
        model.addAttribute("hotelsId",hotelsId);
        model.addAttribute("books",lastFilter);
        return "booking";
    }

    @GetMapping(value = "/bookThis")
     private String bookThis(@RequestParam(name = "typeId") long typeId,
                             @RequestParam(name = "hotelsId") long hotelsId,
                             @RequestParam(name = "date") String date,
                             @RequestParam(name = "priceOfNomer") int priceOfNomer,
                             Model model){

        System.out.println(typeId+" | "+hotelsId+" | "+date+" | "+priceOfNomer);

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == hotelsId){
                hotel=allHotels.get(i);
            }
        }

        model.addAttribute("typeId",typeId);
        model.addAttribute("hotelsId",hotelsId);
        model.addAttribute("date",date);
        model.addAttribute("hotelDetails",hotel.getOwner().getBank_card());
        model.addAttribute("user",user1);
        model.addAttribute("priceOfNomer",priceOfNomer);
        return "paybook";
    }

    @GetMapping(value = "/booked")
    private String booked(@RequestParam(name = "typeId") long typeId,
                          @RequestParam(name = "hotelsId") long hotelsId,
                          @RequestParam(name = "date") String date,
                          @RequestParam(name = "priceOfNomer") int priceOfNomer,
                          Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> allHotels=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).getId() == hotelsId){
                hotel=allHotels.get(i);
            }
        }

        int remindMoney=user1.getBank_card().getMoneyInAccount()-priceOfNomer;
        user1.getBank_card().setMoneyInAccount(remindMoney);
        TypeOfNomer typeOfNomer=typeOfNomerService.getTypeOfNomerById(typeId);
        bookedNomersServicel.addBookedNomer(new BookedNomer(null,date,1,priceOfNomer,user1,typeOfNomer,hotel));

        return "redirect:/mybooks";
    }

    @GetMapping(value = "/favorites")
    public String favorites(Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Hotels> favorites=user1.getFavoriteHotels();

        for (Hotels h: favorites){
            System.out.println(h.getTitle()+" ++++");
        }

        model.addAttribute("favorites",favorites);
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

        user1.getFavoriteHotels().add(hotel);

        userService.updateUser(user1);


        return "redirect:/";
    }

    @GetMapping(value = "/unfavorites")
    public String unfavorites(@RequestParam(name = "hotelsId") long id){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());


        System.out.println(id+" ||");

        List<Hotels> usersFavs=user1.getFavoriteHotels();
        Hotels hotel=new Hotels();
        for (Hotels h: user1.getFavoriteHotels()){
            if (h.getId() == id) {
                hotel = h;
            }
        }
        user1.getFavoriteHotels().remove(hotel);
        userService.updateUser(user1);

        return "redirect:/favorites";
    }

    @GetMapping(value = "/rate/{currentRate}/{allRating}/{hotelsId}")
    public String rate(@PathVariable(name = "currentRate") double currentRate,
                       @PathVariable(name = "allRating") double rating,
                       @PathVariable(name = "hotelsId") long hotelsId,
                       Session session,
                       Model model){

        Users user0= (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user1=userService.getUserByEmail(user0.getEmail());

        List<Voted_Users> voted_users=voted_usersService.getAllVoted_Users();
        List<Voted_Users> usersVotedtoThisHotel=new ArrayList<>();
        for (int i = 0; i < voted_users.size(); i++) {
            if (voted_users.get(i).getHotel().getId() == hotelsId){
                usersVotedtoThisHotel.add(voted_users.get(i));
            }
        }

        double resultRating=0;
        int increment=0;
        double fromStringtoInt=0;
        for (Voted_Users v: usersVotedtoThisHotel ){
            fromStringtoInt=v.getVotedRating();
            resultRating= resultRating+v.getVotedRating();
            increment++;
        }

        resultRating=resultRating+currentRate;
        increment=increment+1;

        double result=resultRating/increment;

        System.out.println(result+" ||");

        String formatting=String.format("%.1f",result);
        formatting=formatting.replace(",",".");

        System.out.println(formatting+"  |||");

            Double formatting2=Double.valueOf(formatting);
            System.out.println(formatting2);

        List<Hotels> hotelsList=hotelService.getAllHotels();
        Hotels hotel=new Hotels();
        for (int i = 0; i < hotelsList.size(); i++) {
            if (hotelsList.get(i).getId() == hotelsId){
                hotel=hotelsList.get(i);
            }
        }

        Voted_Users voted_users1=new Voted_Users(null,currentRate,hotel,user1);
        voted_usersService.saveVoted_User(voted_users1);

        hotel.setRating(formatting2);
        hotelService.saveOrUpdateHotel(hotel);

        model.addAttribute("id",hotelsId);
        return "redirect:/details/"+hotelsId;
    }











}
