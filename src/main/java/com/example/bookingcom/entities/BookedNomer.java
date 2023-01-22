package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "booked_nomer")
public class BookedNomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookedTime;
    private int days;
    private int totalPrice;
    @ManyToOne
    private Users user;
    @ManyToOne
    private TypeOfNomer nomer;
    @ManyToOne
    private Hotels hotel;

}
