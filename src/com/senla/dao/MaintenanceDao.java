package com.senla.dao;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.model.Maintenance;

public class MaintenanceDao extends AbstractDao<Maintenance> implements IMaintenanceDao { // Implementing the MaintenanceDao interface

    /* Most of the methods are implemented in AbstractDao */

    @Override
    public Maintenance update(Maintenance entity) {
        Maintenance maintenance = getById(entity.getId());
        maintenance.setName(entity.getName());
        maintenance.setPrice(entity.getPrice());

        return maintenance;
    }
}