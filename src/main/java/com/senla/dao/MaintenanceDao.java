package com.senla.dao;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.model.Maintenance;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao {

    @Override
    public Maintenance getById(Long id){
        return new Maintenance(super.getById(id));
    }

    @Override
    public Maintenance update(Maintenance entity) {
        Maintenance maintenance = super.getById(entity.getId());
        maintenance.setName(entity.getName());
        maintenance.setPrice(entity.getPrice());
        return maintenance;
    }
}
