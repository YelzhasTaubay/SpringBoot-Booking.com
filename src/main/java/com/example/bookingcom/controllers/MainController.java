package com.example.bookingcom.controllers;

import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CityService cityService;

    @GetMapping(value = {"","/"})
    public String mainMenu(Model model){
        List<Cities> allCities=cityService.getAllCities();
        model.addAttribute("cities",allCities);
        return "mainMenu";
    }

    @GetMapping(value = "/details")
    public String details(Model model){

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
    public String logining(@RequestParam(name = "user_name") String email,
                           @RequestParam(name = "user_password") String password){

        System.out.println(email+"   email");
        System.out.println(password+"  pass");

        return "redirect:/";
    }





}
