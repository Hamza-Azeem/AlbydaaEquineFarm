package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.base.BaseService;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Horse;

import java.util.List;

public interface HorseService extends BaseService<Horse> {
    public HorseDTO giveResponsibilityToDoctor(long doctorId, long horseId);
    public HorseDTO addParentToHorse(long childId, long parentId);
    public HorseDTO findHorseById(long id);
    public List<HorseDTO> findAllHorses();
    public HorseDTO saveHorse(HorseDTO horseDTO);
    public HorseDTO updateHorse(HorseDTO horseDTO);
    public List<HorseDTO> findChildrenOfHorse(long id);

}
