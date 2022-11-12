package com.example.bookingcom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelsDTO {

    private Long id;
    private String name;
    private String title;
    private String description;
    private List<CountryDTO> country;
    private TypeOfHotelDTO typeOfHotel;
    private List<ComfortOfHotelDTO> comforts;
    private String street;
    private int rating;
    private int price;
    private int amountOfNomers;
    private List<PhotosOfHotelDTO> photos;

}
