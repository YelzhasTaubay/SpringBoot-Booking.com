package com.example.bookingcom.dto;

import com.example.bookingcom.entities.Hotels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksOfUserDTO {

    private Long id;
    private String name;
    private Timestamp bookedTime;
    private int days;
    private int cashForBook;
    private StatusDTO status;
    private HotelsDTO hotel;

}
