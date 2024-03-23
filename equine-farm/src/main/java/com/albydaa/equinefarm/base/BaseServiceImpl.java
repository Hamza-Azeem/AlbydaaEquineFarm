package com.albydaa.equinefarm.base;


import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseRepo<T> baseRepo;

    @Override
    public T findObjectById(long id) {
        Optional<T> optionalT = baseRepo.findById(id);
        if(optionalT.isEmpty()){
            return null; // Handle it when adding exception handling
        }
        return optionalT.get();
    }

    @Override
    public List<T> findAllObjects() {
        return baseRepo.findAll();
    }

    @Override
    public T saveObject(T object) {
        return baseRepo.save(object);
    }
    @Override
    public T updateExistingObject(T object) {
        return baseRepo.save(object);
    }
    @Override

    public void deleteObjectById(long id) {
        baseRepo.deleteById(id);
    }
}
