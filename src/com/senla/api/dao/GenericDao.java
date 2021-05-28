package com.senla.api.dao;

import com.senla.model.AEntity;

import java.util.List;

public interface GenericDao <T extends AEntity>{

    /* Parent database interface <T extends AEntity> - generic to work with any descendant of the AEntity class */

    void save(T entity);

    T getById(Long id);

    List<T> getAll();

    void delete(T entity);

    T update(T entity);
}
