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
@Table(name = "typeOfHotel")
public class TypeOfHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToMany(fetch = FetchType.EAGER)     //Здесь кажется нужно менять Мани то Мани
    private List<TypeOfNomer> typeOfNomers;



}
