package com.senla.dao;

import com.senla.api.dao.GenericDao;
import com.senla.model.AEntity;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Room;
import com.senla.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> { // Реализация методов GenericDao

    List <T> repository = new ArrayList<>(); // Хранилище для абстрактной сущности (замена DB)

    @Override
    public void save(T entity) {
        if(entity instanceof Guest){
            entity.setId(IdGenerator.generateGuestId());
        } else if (entity instanceof Maintenance){
            entity.setId(IdGenerator.generateMaintenanceId());
        } else if (entity instanceof Room){
            entity.setId(IdGenerator.generateRoomId());
        }
        repository.add(entity); }

    @Override
    public T getById(Long id) {
        for(T entity : repository){
            if(id.equals(entity.getId())){
                return entity;
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return repository;
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

//    @Override
//    public T update(T entity) {
//        return null;
//    }
}
