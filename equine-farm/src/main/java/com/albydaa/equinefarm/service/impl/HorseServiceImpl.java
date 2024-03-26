package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.exception.InvalidInputException;
import com.albydaa.equinefarm.exception.RecordNotFoundException;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.HorseRepo;
import com.albydaa.equinefarm.service.DoctorService;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
        Horse horse = findObjectById(id, "horse");
        if(horse.getChildren() != null){
            return horse.getChildren().stream().map(h -> mapToHorseDTO(h)).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public List<HorseDTO> findParentsOfHorse(long id) {
        Horse horse = findObjectById(id, "horse");
        if (horse.getParents() != null){
            return horse.getParents().stream().map(h -> mapToHorseDTO(h)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public DoctorDTO findDoctorResponsible(long id) {
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty())
            throw  new RecordNotFoundException("No horse record with id=%s was found!".formatted(id));
        Horse horse = optionalHorse.get();
        return horse.getDoctorInCharge() != null ? mapToDoctorDTO(horse.getDoctorInCharge()) : null;
    }

    @Override
    public HorseDTO findHorseById(long id){
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty()){
            throw  new RecordNotFoundException("No horse record with id=%s was found!".formatted(id));
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
        return null;
    }

    @Override
    public HorseDTO saveHorse(HorseDTO horseDTO) {
        Horse horse = mapToHorse(horseDTO);
        if(horse.getId() != null){
            throw new InvalidInputException("Invalid use of the save method. Try update method?");
        }
        horseRepo.save(horse);
        horseDTO.setId(horse.getId());
        return horseDTO;
    }

    @Override
    public HorseDTO updateHorse(HorseDTO horseDTO) {
        if(horseDTO.getId() == null){
            throw new InvalidInputException("Invalid use of the update method. Try save method?");
        }
        else if(horseRepo.findById(horseDTO.getId()).isEmpty()){
            throw new InvalidInputException("No horse record was found with id=%s".formatted(horseDTO.getId()));
        }
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
            throw  new RecordNotFoundException("No horse record with id=%s was found!".formatted(horseId));
        }
        Horse horse = horseOptional.get();
        Doctor doctor = doctorService.findObjectById(doctorId, "doctor");
        horse.setDoctorInCharge(doctor);
        saveObject(horse);
        doctor.addHorse(horse);
        doctorService.saveObject(doctor);
        HorseDTO horseDTO = mapToHorseDTO(horse);
        return horseDTO;
    }

    @Override
    public int addParentToHorse(long childId, long parentId) {
        if(childId == parentId){
            return 0;
        }
        Horse parent = findObjectById(parentId, "horse(parent)");
        Horse child = findObjectById(childId, "horse(child)");
        if(child.getChildren().contains(parent)){
            throw new InvalidInputException("Invalid relation between child and parent!");
        }
        Set<Horse> parents = child.getParents();
        if(parents.size() >= 2){
            throw new InvalidInputException("Child already has two parents!");
        }
        return horseRepo.addParentToHorse(childId, parentId);
    }
}
