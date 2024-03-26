package com.albydaa.equinefarm.mapper;

import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Horse;

import java.util.stream.Collectors;

import static com.albydaa.equinefarm.mapper.DoctorMapper.*;

public class HorseMapper {
    public static Horse mapToHorse(HorseDTO dto){
        Horse horse = Horse.builder()
                .id(dto.getId())
                .breed(dto.getBreed())
                .age(dto.getAge())
                .price(dto.getPrice())
                .name(dto.getName())
                .build();
        return horse;
    }
    public static HorseDTO mapToHorseDTO(Horse horse){
        HorseDTO horseDTO = HorseDTO.builder()
                .id(horse.getId())
                .breed(horse.getBreed())
                .age(horse.getAge())
                .price(horse.getPrice())
                .name(horse.getName())
                .build();
        return horseDTO;
    }
}
