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

    static {
        MaintenanceService maintenanceService = MaintenanceService.getINSTANCE();
        maintenanceService.addMaintenance("Room cleaning", 50.0);
        maintenanceService.addMaintenance("Cleaning clothes", 10.0);
        maintenanceService.addMaintenance("Shoe shine", 5.0);
        maintenanceService.addMaintenance("Food ordering", 75.0);
        maintenanceService.addMaintenance("Call order", 3.0);
        maintenanceService.addMaintenance("Intercity call order", 10.0);
        maintenanceService.addMaintenance("Cable TV order", 30.0);
        maintenanceService.addMaintenance("Pay channel order", 3.0);
        maintenanceService.addMaintenance("Morning Wake Order", 2.0);
        maintenanceService.addMaintenance("Mini bar", 120.0);
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
