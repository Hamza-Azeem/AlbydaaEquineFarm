package com.albydaa.equinefarm.repository;

import com.albydaa.equinefarm.base.BaseRepo;
import com.albydaa.equinefarm.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends BaseRepo<Doctor> {
}
