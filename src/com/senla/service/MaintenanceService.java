package com.senla.service;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.service.IMaintenanceService;
import com.senla.model.Maintenance;
import com.senla.util.InitializerDAO;

import java.util.List;

public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao = InitializerDAO.MAINTENANCE_DAO;

//    public MaintenanceService(IMaintenanceDao maintenanceDao) {
//        this.maintenanceDao = maintenanceDao;
//    }


    protected MaintenanceService() {
    }

    private static MaintenanceService INSTANCE;

    public static MaintenanceService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MaintenanceService();
        }
        return INSTANCE;
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

    @Override
    public Maintenance getById(Long maintenanceId) {
        return maintenanceDao.getById(maintenanceId);
    }

    @Override
    public List<Maintenance> getAll() {
        return maintenanceDao.getAll();
    }
}
