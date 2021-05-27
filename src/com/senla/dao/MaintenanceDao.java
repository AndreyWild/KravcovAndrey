package com.senla.dao;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.model.Maintenance;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {
//    @Override
//    public void save(Maintenance entity) {
//
//    }
//
//    @Override
//    public Maintenance getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<Maintenance> getAll() {
//        return null;
//    }
//
//    @Override
//    public void delete(Maintenance entity) {
//
//    }

    @Override
    public Maintenance update(Maintenance entity) {
        Maintenance maintenance = getById(entity.getId()); // создаем новый объект Гость и присваиваем ему id (из параметра)
        maintenance.setName(entity.getName()); // меняем ему имя (из параметра)
        maintenance.setPrice(entity.getPrice()); // и возраст
        return maintenance;
    }
}
