package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.dtos.DoctorDTO;
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
import java.util.Set;
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
        List<Horse> horses = horseRepo.findChildrenByParentsId(id);
        if(horses != null){
            return horses.stream().map(horse -> mapToHorseDTO(horse)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<HorseDTO> findParentsOfHorse(long id) {
        List<Horse> horses = horseRepo.findParentsByChildrenId(id);
        if (horses != null){
            return horses.stream().map(h -> mapToHorseDTO(h)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public DoctorDTO findDoctorResponsible(long id) {
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty())
            return null; // handle
        Horse horse = optionalHorse.get();
        return horse.getDoctorInCharge() != null ? mapToDoctorDTO(horse.getDoctorInCharge()) : null;
    }

    @Override
    public HorseDTO findHorseById(long id){
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty()){
            return null; // handle later when adding exception handling
        }
        Horse horse = optionalHorse.get();
        HorseDTO horseDTO = mapToHorseDTO(horse);
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
        horseRepo.save(horse);
        horseDTO.setId(horse.getId());
        return horseDTO;
    }

    @Override
    public HorseDTO updateHorse(HorseDTO horseDTO) {
        // handle if horse doesn't exist in database.
        Horse horse = mapToHorse(horseDTO);
        horseRepo.save(horse);
        horseDTO = mapToHorseDTO(horse);
        return horseDTO;
    }


// update *************
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
        HorseDTO horseDTO = mapToHorseDTO(horse);;
        return horseDTO;
    }

    @Override
    public int addParentToHorse(long childId, long parentId) {
        if(childId == parentId){
            return 0;
        }
        Horse parent = horseRepo.findById(parentId).get();
        Horse child = horseRepo.findById(childId).get();
        if(child.getChildren().contains(parent)){
            return 0;
        }
        Set<Horse> parents = child.getParents();
        if(parents.size() < 2){
            return horseRepo.addParentToHorse(childId, parentId);
        }
        return 0;
    }
}
