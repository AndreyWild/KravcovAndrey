package com.senla.service;

import com.senla.api.dao.IMaintenanceDao;
import com.senla.api.service.IMaintenanceService;
import com.senla.model.Maintenance;
import com.senla.util.InitializerDAO;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MaintenanceService implements IMaintenanceService {

    private final IMaintenanceDao maintenanceDao = InitializerDAO.MAINTENANCE_DAO;

    protected MaintenanceService() {}

    private static MaintenanceService instance;

    public static MaintenanceService getInstance() {
        return Objects.requireNonNullElse(instance, new MaintenanceService());
    }

    static {
        MaintenanceService maintenanceService = MaintenanceService.getInstance();
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
    public List<Maintenance> getAll() {
        return maintenanceDao.getAll();
    }

    @Override
    public Maintenance getMaintenanceById(Long maintenanceId) {
        return maintenanceDao.getById(maintenanceId);
    }

    @Override
    public List<Maintenance> getAll(Comparator<Maintenance> comp) {
        List<Maintenance> maintenances = maintenanceDao.getAll();
        maintenances.sort(comp);
        return maintenances;
    }
}
