package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.HorseRepo;
import com.albydaa.equinefarm.service.DoctorService;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.albydaa.equinefarm.mapper.DoctorMapper.mapToDoctor;
import static com.albydaa.equinefarm.mapper.DoctorMapper.mapToDoctorDTO;
import static com.albydaa.equinefarm.mapper.HorseMapper.mapToHorse;
import static com.albydaa.equinefarm.mapper.HorseMapper.mapToHorseDTO;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl extends BaseServiceImpl<Horse> implements HorseService {
    private final HorseRepo horseRepo;
    private final DoctorService doctorService;

    @Override
    public List<HorseDTO> findChildrenOfHorse(long id) {
        List<Horse> horses = horseRepo.findChildrenOfHorse(id);
        if(horses != null){
            return horses.stream().map(horse -> mapToHorseDTO(horse)).collect(Collectors.toList());
        }
        return null;
    }
    @Override
    public HorseDTO findHorseById(long id){
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty()){
            return null; // handle later when adding exception handling
        }
        Horse horse = optionalHorse.get();
        HorseDTO horseDTO = mapToHorseDTO(horse);
        if(horse.getDoctorInCharge() != null){
            horseDTO.setDoctorInCharge(mapToDoctorDTO(doctorService.findObjectById(horse.getDoctorInCharge().getId()))); // handle if doctor is null
        }
        horseDTO.setParent(horse.getParent() != null ? mapToHorseDTO(horseRepo.findById(horse.getParent().getId()).get()): null);
        return horseDTO;
    }

    @Override
    public List<HorseDTO> findAllHorses() {
        List<Horse> horses = horseRepo.findAll();
        if(horses != null){
            return horses.stream().map(horse -> mapToHorseDTO(horse)).collect(Collectors.toList());
        }
        return null; // handle later.
    }

    @Override
    public HorseDTO saveHorse(HorseDTO horseDTO) {
        Horse horse = mapToHorse(horseDTO);
        if(horseDTO.getParent() != null){
            horse.setParent(mapToHorse(horseDTO.getParent()));
        }
        if(horseDTO.getDoctorInCharge() != null) {
            horse.setDoctorInCharge(mapToDoctor(horseDTO.getDoctorInCharge()));
        }
        horseRepo.save(horse);
        horseDTO.setId(horse.getId());
        return horseDTO;
    }

    @Override
    public HorseDTO updateHorse(HorseDTO horseDTO) {
        Horse horse = mapToHorse(horseDTO);
        if(horseDTO.getParent() != null){
            horse.setParent(mapToHorse(horseDTO.getParent()));
        }
        if(horseDTO.getDoctorInCharge() != null){
            horse.setDoctorInCharge(mapToDoctor(horseDTO.getDoctorInCharge()));
        }
        horseRepo.save(horse);
        horseDTO = mapToHorseDTO(horse);
        return horseDTO;
    }



    @Override
    public HorseDTO giveResponsibilityToDoctor(long doctorId, long horseId) {
        Optional<Horse> horseOptional = horseRepo.findById(horseId);
        if(horseOptional.isEmpty()){
            return null; // handle later
        }
        Horse horse = horseOptional.get();
        Doctor doctor = doctorService.findObjectById(doctorId);
        horse.setDoctorInCharge(doctor);
        saveObject(horse);
        doctor.addHorse(horse);
        doctorService.saveObject(doctor);
        HorseDTO horseDTO = mapToHorseDTO(horse);
        horseDTO.setDoctorInCharge(mapToDoctorDTO(doctor));
        return horseDTO;
    }

    @Override
    public HorseDTO addParentToHorse(long childId, long parentId) {
        Horse child = horseRepo.findById(childId).get();
        Horse parent = horseRepo.findById(parentId).get();
        if(childId == parentId){
            return null;
        }
        child.setParent(parent);
        parent.addChild(child);
        saveObject(parent);
        saveObject(child);
        HorseDTO childDTO = mapToHorseDTO(child);
        childDTO.setParent(mapToHorseDTO(parent));
        return childDTO;
    }
}
