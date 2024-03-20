package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;

import java.util.List;
import java.util.Optional;

public interface HorseService {
    public Optional<Horse> findHorseById(long id);
    public List<Horse> findAllHorses();
    public Horse addHorse(Horse horse);
    public Horse updateExistingHorse(Horse horse);
    public void deleteHorseById(long id);
    public Horse giveResponsibilityToDoctor(long doctorId, long horseId);
    public Horse addParentToHorse(long childId, long parentId);
}
