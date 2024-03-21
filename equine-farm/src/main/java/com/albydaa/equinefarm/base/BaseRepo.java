package com.albydaa.equinefarm.base;

import com.albydaa.equinefarm.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity> extends JpaRepository<T, Long> {
}
