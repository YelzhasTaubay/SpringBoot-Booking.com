package com.example.bookingcom.dto.mapper;

import com.example.bookingcom.dto.CityDTO;
import com.example.bookingcom.entities.Cities;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CityMapper {

    CityMapper MAPPER= Mappers.getMapper(CityMapper.class);

    CityDTO toCityDTO(Cities city);
    Cities toCity(CityDTO cityDTO);

    List<CityDTO> toCityDTOList(List<Cities> cities);
    List<Cities> toCitiesList(List<CityDTO> cityDTOS);


}
