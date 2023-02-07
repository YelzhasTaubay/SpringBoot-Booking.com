package com.example.bookingcom.mapper;

import com.example.bookingcom.dto.HotelsDto;
import com.example.bookingcom.entities.Hotels;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelsDto toHotelsDto(Hotels hotel);

    Hotels toHotelFromDto(HotelsDto hotelsDto);

    List<HotelsDto> toHotelsListDto(List<Hotels> hotels);

    List<Hotels> toHotelsListFromDto(List<HotelsDto> hotelsDtos);


}
