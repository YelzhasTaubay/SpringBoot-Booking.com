package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private String title;
    @Column(columnDefinition = "Text")
    private String description;
    private String street;
    @Column(nullable = true,updatable = true,insertable = true)
    private double rating;
//    @ColumnDefault("0")
//    private int usersVote;
    @ManyToOne
    private Users owner;
    @ManyToMany
    private List<Country> country;
    @OneToOne(fetch = FetchType.LAZY)
    private TypeOfHotel typeOfHotel;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ComfortsOfHotel> comforts;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<StuffsOfHotel> stuffsOfHotels;
    @OneToMany(fetch = FetchType.LAZY)
    private List<PhotosOfHotel> photos;






}
