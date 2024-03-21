package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.base.BaseService;
import com.albydaa.equinefarm.model.Horse;

public interface HorseService extends BaseService<Horse> {
    public Horse giveResponsibilityToDoctor(long doctorId, long horseId);
    public Horse addParentToHorse(long childId, long parentId);
}
