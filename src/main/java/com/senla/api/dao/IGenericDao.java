package com.senla.api.dao;

import com.senla.model.AEntity;
import com.senla.model.Guest;

import java.util.List;

public interface IGenericDao<T extends AEntity>{

    void save(T entity);

    T getById(Long id);

    List<T> getAll();

    void delete(T entity);

    T update(T entity);

    void setList(List<T> repository);
}
