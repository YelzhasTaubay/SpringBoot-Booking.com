package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "booksOfUser")
public class BooksOfUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Timestamp bookedTime;
    private int days;
    private int cashForBook;
    @OneToOne(fetch = FetchType.EAGER)
    private Status status;
    @OneToOne(fetch = FetchType.EAGER)
    private Hotels hotel;








}
