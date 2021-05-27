package com.senla.service;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.service.IMaintenanceService;
import com.senla.model.Maintenance;

import java.util.List;

public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao;

    public MaintenanceService(IMaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public Maintenance addMaintenance(String name, Double price) {
        Maintenance maintenance = new Maintenance(name, price);
        maintenanceDao.save(maintenance);
        return maintenance;
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceDao.getAll();
    }
}
