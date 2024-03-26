package com.albydaa.equinefarm.controller;

import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Doctor.Specialization;
import com.albydaa.equinefarm.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/{id}")
    public DoctorDTO getDoctorWithId(@PathVariable long id){
        return doctorService.findDoctorById(id);
    }
    @GetMapping
    public List<DoctorDTO> getAllDoctors(){
        return doctorService.findAllDoctors();
    }
    @PostMapping
    public DoctorDTO createDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.saveDoctor(doctorDTO);
    }
    @PutMapping
    public DoctorDTO updateDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.updateDoctor(doctorDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteDoctorWithId(@PathVariable long id){
        doctorService.deleteObjectById(id);
    }
    // *********************************************
    // customized endpoints
    @GetMapping("/{id}/managed-horses")
    public List<HorseDTO> findHorsesManagedByDoctor(@PathVariable long id){
        return doctorService.findAllHorsesManagedByDoctor(id);
    }
    @GetMapping("/specialization/{specialization}")
    public List<DoctorDTO> findAllDoctorsWithSpecialization(@PathVariable Specialization specialization){
        return doctorService.findAllDoctorsWithSpecialization(specialization);
    }
}
