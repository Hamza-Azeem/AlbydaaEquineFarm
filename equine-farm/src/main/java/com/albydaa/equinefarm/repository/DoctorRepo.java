package com.albydaa.equinefarm.repository;

import com.albydaa.equinefarm.base.BaseRepo;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Doctor.Specialization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends BaseRepo<Doctor> {
    List<Doctor> findDoctorBySpecialization(Specialization specialization);
}
