package com.albydaa.equinefarm.base;

import java.util.List;

public interface BaseService<T> {
    public T findObjectById(long id, String className);

    public List<T> findAllObjects();

    public T saveObject(T object);
    public T updateExistingObject(T object);

    public void deleteObjectById(long id);
}
