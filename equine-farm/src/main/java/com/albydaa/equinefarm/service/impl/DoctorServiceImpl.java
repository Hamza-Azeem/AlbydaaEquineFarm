package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.exception.InvalidInputException;
import com.albydaa.equinefarm.exception.RecordNotFoundException;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Doctor.Specialization;
import com.albydaa.equinefarm.repository.DoctorRepo;
import com.albydaa.equinefarm.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
        List<HorseDTO> horseDTOS = doctor.getManagedHorses().stream().map( // check if doctor has no horses
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
        Optional<Doctor> optionalDoctor = repo.findById(doctorId);
        if(optionalDoctor.isEmpty()){
            throw  new RecordNotFoundException("No doctor record was found with id=%s!".formatted(doctorId));
        }
        Doctor doctor = optionalDoctor.get();
        return mapToDoctorDTO(doctor);
    }

    @Override
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        if(doctorDTO.getId() != null){
            throw new InvalidInputException("Invalid use of the save method. Try update method?");
        }
        Doctor doctor = mapToDoctor(doctorDTO);
        repo.save(doctor);
        doctorDTO.setId(doctor.getId());
        return doctorDTO;
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        if(doctorDTO.getId() == null){
            throw new InvalidInputException("Invalid use of the update method. Try save method?");
        }
        else if(repo.findById(doctorDTO.getId()).isEmpty()){
            throw new InvalidInputException("No doctor record was found with id=%s".formatted(doctorDTO.getId()));
        }
        Doctor doctor = mapToDoctor(doctorDTO);
        repo.save(doctor);
        doctorDTO = mapToDoctorDTO(doctor);
        return doctorDTO;
    }


}
