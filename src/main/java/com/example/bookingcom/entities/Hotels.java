package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotels")
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private String description;

    @ManyToMany
    private List<Country> country;

    @OneToOne(fetch = FetchType.LAZY)
    private TypeOfHotel typeOfHotel;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ComfortsOfHotel> comforts;

    private int rating;

    private int price;

    private int amountOfNomers;





}
