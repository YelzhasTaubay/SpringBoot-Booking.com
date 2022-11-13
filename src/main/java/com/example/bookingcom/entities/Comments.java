package com.example.bookingcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "Text")
    private String comment;
    private LocalDateTime timeOfComment;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotels hotel;



}
