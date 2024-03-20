package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.HorseRepo;
import com.albydaa.equinefarm.service.DoctorService;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {
    private final HorseRepo repo;
    private final DoctorService doctorService;
    @Override
    public Optional<Horse> findHorseById(long id) {
        return repo.findById(id);
    }
    @Override
    public List<Horse> findAllHorses() {
        return repo.findAll();
    }

    @Override
    public Horse addHorse(Horse horse) {
        return repo.save(horse);
    }

    @Override
    public Horse updateExistingHorse(Horse horse) {
        return repo.save(horse);
    }

    @Override
    public void deleteHorseById(long id) {
        repo.deleteById(id);
    }

    @Override
    public Horse giveResponsibilityToDoctor(long doctorId, long horseId) {
        Optional<Horse> optionalHorse = findHorseById(horseId);
        Optional<Doctor> optionalDoctor = doctorService.findDoctorById(doctorId);
        if(optionalHorse.isEmpty()){
           return null; // change it and do something when adding exception handling.
        }else if(optionalDoctor.isEmpty()){
            return null; // change it and do something when adding exception handling.
        }
        Horse horse = optionalHorse.get();
        Doctor doctor = optionalDoctor.get();
        horse.setDoctorInCharge(doctor);
        updateExistingHorse(horse);
        doctor.addHorse(horse);
        doctorService.saveDoctor(doctor);
        return horse;
    }

    @Override
    public Horse addParentToHorse(long childId, long parentId) {
        Optional<Horse> optionalChild = findHorseById(childId);
        Optional<Horse> optionalParent = findHorseById(parentId);
        if(optionalChild.isEmpty()){
            return null; // change it and do something when adding exception handling.
        }else if (optionalParent.isEmpty()){
            return null; // change it and do something when adding exception handling.
        }else if(childId == parentId){
            return null; // change it and do something when adding exception handling.
        }
        Horse child = optionalChild.get();
        Horse parent = optionalParent.get();
        child.setParent(parent);
        parent.addChild(child);
        updateExistingHorse(parent);
        updateExistingHorse(child);
        return child;
    }
}
