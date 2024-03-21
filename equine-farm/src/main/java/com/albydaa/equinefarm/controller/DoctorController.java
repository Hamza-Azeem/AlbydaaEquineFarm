package com.albydaa.equinefarm.controller;

import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/{id}")
    public Doctor getDoctorWithId(@PathVariable long id){
        return doctorService.findObjectById(id);
    }
    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.findAllObjects();
    }
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorService.saveObject(doctor);
    }
    @PutMapping
    public Doctor updateDoctor(@RequestBody Doctor doctor){
        return doctorService.saveObject(doctor);
    }
    @DeleteMapping("/{id}")
    public void deleteDoctorWithId(@PathVariable long id){
        doctorService.deleteObjectById(id);
    }
}
