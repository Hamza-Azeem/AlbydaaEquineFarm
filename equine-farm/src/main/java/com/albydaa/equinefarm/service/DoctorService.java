package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorService{
    public Optional<Doctor> findDoctorById(long id);
    public List<Doctor> findAllDoctors();
    public Doctor saveDoctor(Doctor doctor);
    public Doctor updateExistingDoctor(Doctor doctor);
    public void deleteDoctorById(long id);
}
