package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Doctor.Specialization;
import com.albydaa.equinefarm.repository.DoctorRepo;
import com.albydaa.equinefarm.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

import static com.albydaa.equinefarm.mapper.DoctorMapper.mapToDoctor;
import static com.albydaa.equinefarm.mapper.DoctorMapper.mapToDoctorDTO;
import static com.albydaa.equinefarm.mapper.HorseMapper.mapToHorseDTO;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl extends BaseServiceImpl<Doctor> implements DoctorService {
    private final DoctorRepo repo;
    @Override
    public List<DoctorDTO> findAllDoctorsWithSpecialization(Specialization specialization) {
        List<Doctor> doctors = repo.findDoctorBySpecialization(specialization);
        if(doctors != null){
            return doctors.stream().map(doctor -> mapToDoctorDTO(doctor))
                    .collect(Collectors.toList());
        }
        return null;
    }
    @Override
    public List<HorseDTO> findAllHorsesManagedByDoctor(long doctorId) {
        Doctor doctor = repo.findById(doctorId).get();
        List<HorseDTO> horseDTOS = doctor.getManagedHorses().stream().map(
                horse -> mapToHorseDTO(horse)
        ).collect(Collectors.toList());
        return horseDTOS;
    }

    @Override
    public List<DoctorDTO> findAllDoctors() {
        List<Doctor> doctors = repo.findAll();
        if(doctors != null){
            return doctors.stream().map(doctor -> mapToDoctorDTO(doctor))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public DoctorDTO findDoctorById(long doctorId) {
        Doctor doctor = repo.findById(doctorId).get();
        return mapToDoctorDTO(doctor);
    }

    @Override
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapToDoctor(doctorDTO);
        repo.save(doctor);
        doctorDTO.setId(doctor.getId());
        return doctorDTO;
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapToDoctor(doctorDTO);
        repo.save(doctor);
        doctorDTO = mapToDoctorDTO(doctor);
        return doctorDTO;
    }


}
