package com.example.bookingcom.controllers;

import com.example.bookingcom.entities.Cities;
import com.example.bookingcom.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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





}
