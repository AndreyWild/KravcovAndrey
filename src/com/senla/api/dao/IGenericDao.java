package com.senla.api.dao;

import com.senla.model.AEntity;

import java.util.List;

public interface IGenericDao<T extends AEntity>{

    void save(T entity);

    T getById(Long id);

    List<T> getAll();

    void delete(T entity);

    T update(T entity);
}
