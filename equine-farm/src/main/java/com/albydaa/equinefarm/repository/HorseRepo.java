package com.albydaa.equinefarm.repository;

import com.albydaa.equinefarm.base.BaseRepo;

import com.albydaa.equinefarm.model.Horse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HorseRepo extends BaseRepo<Horse> {
    @Transactional
    @Modifying
    @Query(value = "INSERT " +
            "INTO child_parent (child_id, parent_id)" +
            " VALUES(:childId, :parentId)",
            nativeQuery = true)
    public int addParentToHorse(long childId, long parentId);
}
