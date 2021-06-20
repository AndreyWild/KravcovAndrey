package com.senla.dao;

import com.senla.api.dao.IGenericDao;
import com.senla.model.AEntity;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Room;
import com.senla.util.exceptions.DaoException;
import com.senla.util.IdGenerator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements IGenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    List<T> repository = new ArrayList<>(); // Storage for abstract entity (DB replacement)

    @Override
    public void save(T entity) {
        if (entity instanceof Guest) {
            entity.setId(IdGenerator.generateGuestId());
        } else if (entity instanceof Maintenance) {
            entity.setId(IdGenerator.generateMaintenanceId());
        } else if (entity instanceof Room) {
            entity.setId(IdGenerator.generateRoomId());
        }
        repository.add(entity);
    }

    @Override
    public T getById(Long id) {
        LOGGER.info(String.format("Launch getById(%s)", id));
        for (T entity : repository) {
            if (id.equals(entity.getId())) {
                return entity;
            }
        }
        LOGGER.warn(String.format("getById(%s) - failed! There is no object with this index!", id));
        throw new DaoException("There is no object with this index!");
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
