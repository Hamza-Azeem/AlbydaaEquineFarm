package com.albydaa.equinefarm.dtos;

import com.albydaa.equinefarm.base.BaseEntity;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class HorseDTO {
    private Long id;
    private String name;
    private String breed;
    private Double price;
    private Integer age;
    private HorseDTO parent;
    private List<HorseDTO> children = new ArrayList<>();
    private DoctorDTO doctorInCharge;
}
