package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.repository.DoctorRepo;
import com.albydaa.equinefarm.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo repo;
    @Override
    public Optional<Doctor> findDoctorById(long id) {
        return repo.findById(id);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return repo.findAll();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    @Override
    public Doctor updateExistingDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    @Override
    public void deleteDoctorById(long id) {
        repo.deleteById(id);
    }

}
