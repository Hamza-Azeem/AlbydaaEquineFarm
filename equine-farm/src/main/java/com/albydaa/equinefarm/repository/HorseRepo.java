package com.albydaa.equinefarm.repository;

import com.albydaa.equinefarm.base.BaseRepo;
import com.albydaa.equinefarm.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorseRepo extends BaseRepo<Horse> {
}
