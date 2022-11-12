package com.example.bookingcom.dto.mapper;

import com.example.bookingcom.dto.HotelsDTO;
import com.example.bookingcom.entities.Hotels;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelsMapper {

    HotelsDTO toHotelsDTO(Hotels hotel);
    Hotels toHotels(HotelsDTO hotelsDTO);

    List<HotelsDTO> toHotelsDTO(List<Hotels> hotelsList);
    List<Hotels> toHotels(List<HotelsDTO> hotelsDTOList);


}
