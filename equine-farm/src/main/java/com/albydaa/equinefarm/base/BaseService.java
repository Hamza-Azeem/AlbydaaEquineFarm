package com.albydaa.equinefarm.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    public T findObjectById(long id);

    public List<T> findAllObjects();

    public T saveObject(T object);
    public T updateExistingObject(T object);

    public void deleteObjectById(long id);
}
