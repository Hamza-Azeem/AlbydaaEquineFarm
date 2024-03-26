package com.albydaa.equinefarm.base;


import com.albydaa.equinefarm.exception.RecordNotFoundException;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseRepo<T> baseRepo;

    @Override
    public T findObjectById(long id, String className) {
        Optional<T> optionalT = baseRepo.findById(id);
        if(optionalT.isEmpty() || optionalT == null){
            throw new RecordNotFoundException("No %s record was found with id=%s!".formatted(className, id));
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
