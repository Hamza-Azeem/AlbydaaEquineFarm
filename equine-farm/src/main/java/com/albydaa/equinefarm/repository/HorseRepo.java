package com.albydaa.equinefarm.repository;

import com.albydaa.equinefarm.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepo extends JpaRepository<Horse, Long> {
}
