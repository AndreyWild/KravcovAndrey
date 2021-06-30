package com.senla.dao;

import com.senla.api.dao.IGenericDao;
import com.senla.model.AEntity;
import com.senla.model.Guest;
import com.senla.model.Maintenance;
import com.senla.model.Room;
import com.senla.util.id_generator.api.IGuestIdGenerator;
import com.senla.util.id_generator.api.IMaintenanceIdGenerator;
import com.senla.util.id_generator.api.IRoomIdGenerator;
import com.senla.util.id_generator.IdGenerator;
import com.senla.util.exceptions.EntityNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements IGenericDao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    private IGuestIdGenerator guestIdGenerator = new IdGenerator();
    private IRoomIdGenerator roomIdGenerator = new IdGenerator();
    private IMaintenanceIdGenerator maintenanceIdGenerator = new IdGenerator();

    List<T> repository = new ArrayList<>(); // Storage for abstract entity (DB replacement)

    @Override
    public void save(T entity) {
        if (entity instanceof Guest) {
            entity.setId(guestIdGenerator.generateGuestId());
        } else if (entity instanceof Maintenance) {
            entity.setId(roomIdGenerator.generateRoomId());
        } else if (entity instanceof Room) {
            entity.setId(maintenanceIdGenerator.generateMaintenanceId());
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
        throw new EntityNotFoundException("There is no object with this index!");
    }

    @Override
    public List<T> getAll() {
        return repository;
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

    @Override
    public void setList(List<T> repository) {
        this.repository = repository;
    }

//    @Override
//    public T update(T entity) {
//        return null;
//    }
}
