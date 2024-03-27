package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.base.BaseService;
import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Doctor.Specialization;

import java.util.List;
import java.util.Set;

public interface DoctorService extends BaseService<Doctor> {
    List<HorseDTO> findAllHorsesManagedByDoctor(long doctorId);
    List<DoctorDTO> findAllDoctors();
    DoctorDTO findDoctorById(long doctorId);
    DoctorDTO saveDoctor(DoctorDTO doctorDTO);
    DoctorDTO updateDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> findAllDoctorsWithSpecialization(Specialization specialization);

}
