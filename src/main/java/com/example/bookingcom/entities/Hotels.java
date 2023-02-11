package com.example.bookingcom.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)

    private List<Country> country;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private TypeOfHotel typeOfHotel;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ComfortsOfHotel> comforts;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<StuffsOfHotel> stuffsOfHotels;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<PhotosOfHotel> photos;






}
