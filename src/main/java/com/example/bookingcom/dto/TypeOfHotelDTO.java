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
public class TypeOfHotelDTO {

    private Long id;
    private String type;
    private List<TypeOfNomerDTO> typeOfNomers;


}
