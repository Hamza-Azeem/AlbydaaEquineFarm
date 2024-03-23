package com.albydaa.equinefarm.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseRepo<T> extends JpaRepository<T, Long> {
}
