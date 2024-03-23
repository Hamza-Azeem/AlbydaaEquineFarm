package com.albydaa.equinefarm.mapper;

import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.model.Doctor;

import java.util.stream.Collectors;

import static com.albydaa.equinefarm.mapper.HorseMapper.mapToHorse;
import static com.albydaa.equinefarm.mapper.HorseMapper.mapToHorseDTO;

public class DoctorMapper {

    public static Doctor mapToDoctor(DoctorDTO doctorDTO){
        Doctor doctor = Doctor.builder()
                .id(doctorDTO.getId())
                .firstName(doctorDTO.getFirstName())
                .lastName(doctorDTO.getLastName())
//                .managedHorses(doctorDTO.getManagedHorses() != null ? doctorDTO.getManagedHorses().stream()
//                        .map(horseDTO -> mapToHorse(horseDTO)).collect(Collectors.toList()): null)
                .salary(doctorDTO.getSalary())
                .specialization(doctorDTO.getSpecialization())
                .build();
        return doctor;
    }
    public static DoctorDTO mapToDoctorDTO(Doctor doctor){
        DoctorDTO doctorDTO = DoctorDTO.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
//                .managedHorses(doctor.getManagedHorses() != null ? doctor.getManagedHorses().stream()
//                        .map(horse -> mapToHorseDTO(horse)).collect(Collectors.toList()): null)
                .salary(doctor.getSalary())
                .specialization(doctor.getSpecialization())
                .build();
        return doctorDTO;
    }
}
