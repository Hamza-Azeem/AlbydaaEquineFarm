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
    private final HorseRepo horseRepo;
    private final DoctorService doctorService;
    @Override
    public Horse findObjectById(long id){
        Optional<Horse> optionalHorse = horseRepo.findById(id);
        if(optionalHorse.isEmpty()){
            return null; // handle later when adding exception handling
        }
        Horse horse = optionalHorse.get();
        if(horse.getDoctorInCharge() != null){
            horse.setDoctorInCharge(doctorService.findObjectById(horse.getDoctorInCharge().getId())); // handle if doctor is null
        }
        if(horse.getParent() != null){
            horse.getParent().setDoctorInCharge(null);
            horse.getParent().setParent(null);
        }
        return horse;
    }
    @Override
    public Horse giveResponsibilityToDoctor(long doctorId, long horseId) {
        Horse horse = findObjectById(horseId);
        Doctor doctor = doctorService.findObjectById(doctorId);
        horse.setDoctorInCharge(doctor);
        saveObject(horse);
        doctor.addHorse(horse);
        doctorService.saveObject(doctor);
        return horse;
    }

    @Override
    public Horse addParentToHorse(long childId, long parentId) {
        Horse child = horseRepo.findById(childId).get();
        Horse parent = horseRepo.findById(parentId).get();
        if(childId == parentId){
            return null; // change it and do something when adding exception handling.
        }
        child.setParent(parent);
        parent.addChild(child);
        saveObject(parent);
        saveObject(child);
        return child;
    }
}
