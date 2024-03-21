package com.albydaa.equinefarm.service.impl;

import com.albydaa.equinefarm.base.BaseServiceImpl;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.HorseRepo;
import com.albydaa.equinefarm.service.DoctorService;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl extends BaseServiceImpl<Horse> implements HorseService {
    private final HorseRepo repo;
    private final DoctorService doctorService;
    @Override
    public Horse giveResponsibilityToDoctor(long doctorId, long horseId) {
        Optional<Horse> optionalHorse = repo.findById(horseId);
        Optional<Doctor> optionalDoctor = doctorService.findObjectById(doctorId);
        if(optionalHorse.isEmpty()){
           return null; // change it and do something when adding exception handling.
        }else if(optionalDoctor.isEmpty()){
            return null; // change it and do something when adding exception handling.
        }
        Horse horse = optionalHorse.get();
        Doctor doctor = optionalDoctor.get();
        horse.setDoctorInCharge(doctor);
        repo.save(horse);
        doctor.addHorse(horse);
        doctorService.saveObject(doctor);
        return horse;
    }

    @Override
    public Horse addParentToHorse(long childId, long parentId) {
        Optional<Horse> optionalChild = repo.findById(childId);
        Optional<Horse> optionalParent = repo.findById(parentId);
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
        repo.save(parent);
        repo.save(child);
        return child;
    }
}
